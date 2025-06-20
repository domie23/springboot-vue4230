<template>
	<el-main class="bg edit_wrap">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','statistical_name') || $check_field('add','statistical_name') || $check_field('set','statistical_name')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="统计名称" prop="statistical_name">
					<el-input id="statistical_name" v-model="form['statistical_name']" placeholder="请输入统计名称"
							  v-if="user_group === '管理员' || (form['statistical_report_id'] && $check_field('set','statistical_name')) || (!form['statistical_report_id'] && $check_field('add','statistical_name'))" :disabled="disabledObj['statistical_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','statistical_name')">{{form['statistical_name']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','particular_year') || $check_field('add','particular_year') || $check_field('set','particular_year')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="年份" prop="particular_year">
					<el-input id="particular_year" v-model="form['particular_year']" placeholder="请输入年份"
							  v-if="user_group === '管理员' || (form['statistical_report_id'] && $check_field('set','particular_year')) || (!form['statistical_report_id'] && $check_field('add','particular_year'))" :disabled="disabledObj['particular_year_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','particular_year')">{{form['particular_year']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','month') || $check_field('add','month') || $check_field('set','month')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="月份" prop="month">
					<el-input id="month" v-model="form['month']" placeholder="请输入月份"
							  v-if="user_group === '管理员' || (form['statistical_report_id'] && $check_field('set','month')) || (!form['statistical_report_id'] && $check_field('add','month'))" :disabled="disabledObj['month_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','month')">{{form['month']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','date') || $check_field('add','date') || $check_field('set','date')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="日期" prop="date">
					<el-input id="date" v-model="form['date']" placeholder="请输入日期"
							  v-if="user_group === '管理员' || (form['statistical_report_id'] && $check_field('set','date')) || (!form['statistical_report_id'] && $check_field('add','date'))" :disabled="disabledObj['date_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','date')">{{form['date']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','statistical_quantity') || $check_field('add','statistical_quantity') || $check_field('set','statistical_quantity')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="统计数量" prop="statistical_quantity">
					<el-input-number id="statistical_quantity" v-model.number="form['statistical_quantity']"
						v-if="user_group === '管理员' || (form['statistical_report_id'] && $check_field('set','statistical_quantity')) || (!form['statistical_report_id'] && $check_field('add','statistical_quantity'))" :disabled="disabledObj['statistical_quantity_isDisabled']"></el-input-number>
					<div v-else-if="$check_field('get','statistical_quantity')">{{form['statistical_quantity']}}</div>
				</el-form-item>
			</el-col>
			<el-col :xs="24" :sm="12" :lg="8" class="el_form_btn_warp">
				<el-form-item>
					<el-button type="primary" @click="submit()">提交</el-button>
					<el-button @click="cancel()">取消</el-button>
				</el-form-item>
			</el-col>

		</el-form>
	</el-main>
</template>

<script>
	import mixin from "@/mixins/page.js";

	export default {
		mixins: [mixin],
		data() {
			return {
				field: "statistical_report_id",
				url_add: "~/api/statistical_report/add?",
				url_set: "~/api/statistical_report/set?",
				url_get_obj: "~/api/statistical_report/get_obj?",
				url_upload: "~/api/statistical_report/upload?",

				query: {
					"statistical_report_id": 0,
				},

				form: {
					"statistical_name":'', // 统计名称
					"particular_year":'', // 年份
					"month":'', // 月份
					"date":'', // 日期
					"statistical_quantity":0, // 统计数量
					"statistical_report_id": 0, // ID

				},
				disabledObj:{
					"statistical_name_isDisabled": false,
					"particular_year_isDisabled": false,
					"month_isDisabled": false,
					"date_isDisabled": false,
          "statistical_quantity_isDisabled": false,
				},
			}
		},
		methods: {

			/**
			 * 获取对象之前
			 * @param {Object} param
			 */
			get_obj_before(param) {
				var form = "";
				if(this.form && form){
					Object.keys(this.form).forEach(key => {
						Object.keys(form).forEach(dbKey => {
							// if(dbKey === "charging_standard"){
							// 	this.form['charging_rules'] = form[dbKey];
							// 	this.disabledObj['charging_rules_isDisabled'] = true;
							// };
							if(key === dbKey){
								this.disabledObj[key+'_isDisabled'] = true;
							}
						})
					})
				}
				$.db.del("form");
				return param;
			},

			/**
			 * 获取对象之后
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json, func){
			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/statistical_report/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/statistical_report/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/statistical_report/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/statistical_report/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/statistical_report/view','get');
					console.log(bl ? "你有视图查询权限视作有查询权限" : "你没有视图查询权限");
				}

				console.log(bl ? "具有当前页面的查看权，请注意这不代表你有字段的查看权" : "无权查看当前页，请注意即便有字段查询权限没有页面查询权限也不行");

				return bl;
			},
			/**
			 * 上传文件
			 * @param {Object} param
			 */
			uploadimg(param) {
				this.uploadFile(param.file, "avatar");
			},

		},
		created() {
		},
	}
</script>

<style>
	.avatar-uploader .el-upload {
		border: 1px dashed #d9d9d9;
		border-radius: 6px;
		cursor: pointer;
		position: relative;
		overflow: hidden;
	}

	.avatar-uploader .el-upload:hover {
		border-color: #409EFF;
	}

	.avatar-uploader-icon {
		font-size: 28px;
		color: #8c939d;
		width: 178px;
		height: 178px;
		line-height: 178px;
		text-align: center;
	}

	.avatar {
		width: 178px;
		height: 178px;
		display: block;
	}
</style>
