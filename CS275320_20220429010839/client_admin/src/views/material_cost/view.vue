<template>
	<el-main class="bg edit_wrap">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','material_number') || $check_field('add','material_number') || $check_field('set','material_number')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="材料编号" prop="material_number">
					<el-input id="material_number" v-model="form['material_number']" placeholder="请输入材料编号"
							  v-if="user_group === '管理员' || (form['material_cost_id'] && $check_field('set','material_number')) || (!form['material_cost_id'] && $check_field('add','material_number'))" :disabled="disabledObj['material_number_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','material_number')">{{form['material_number']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','material_name') || $check_field('add','material_name') || $check_field('set','material_name')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="材料名称" prop="material_name">
					<el-input id="material_name" v-model="form['material_name']" placeholder="请输入材料名称"
							  v-if="user_group === '管理员' || (form['material_cost_id'] && $check_field('set','material_name')) || (!form['material_cost_id'] && $check_field('add','material_name'))" :disabled="disabledObj['material_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','material_name')">{{form['material_name']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','unit_price_of_materials') || $check_field('add','unit_price_of_materials') || $check_field('set','unit_price_of_materials')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="材料单价" prop="unit_price_of_materials">
					<el-input id="unit_price_of_materials" v-model="form['unit_price_of_materials']" placeholder="请输入材料单价"
							  v-if="user_group === '管理员' || (form['material_cost_id'] && $check_field('set','unit_price_of_materials')) || (!form['material_cost_id'] && $check_field('add','unit_price_of_materials'))" :disabled="disabledObj['unit_price_of_materials_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','unit_price_of_materials')">{{form['unit_price_of_materials']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','unit_of_calculation') || $check_field('add','unit_of_calculation') || $check_field('set','unit_of_calculation')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="计算单位" prop="unit_of_calculation">
					<el-input id="unit_of_calculation" v-model="form['unit_of_calculation']" placeholder="请输入计算单位"
							  v-if="user_group === '管理员' || (form['material_cost_id'] && $check_field('set','unit_of_calculation')) || (!form['material_cost_id'] && $check_field('add','unit_of_calculation'))" :disabled="disabledObj['unit_of_calculation_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','unit_of_calculation')">{{form['unit_of_calculation']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','remarks') || $check_field('add','remarks') || $check_field('set','remarks')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="备注" prop="remarks">
					<el-input type="textarea" id="remarks" v-model="form['remarks']" placeholder="请输入备注"
						v-if="user_group === '管理员' || (form['material_cost_id'] && $check_field('set','remarks')) || (!form['material_cost_id'] && $check_field('add','remarks'))" :disabled="disabledObj['remarks_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','remarks')">{{form['remarks']}}</div>
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
				field: "material_cost_id",
				url_add: "~/api/material_cost/add?",
				url_set: "~/api/material_cost/set?",
				url_get_obj: "~/api/material_cost/get_obj?",
				url_upload: "~/api/material_cost/upload?",

				query: {
					"material_cost_id": 0,
				},

				form: {
					"material_number":'', // 材料编号
					"material_name":'', // 材料名称
					"unit_price_of_materials":'', // 材料单价
					"unit_of_calculation":'', // 计算单位
					"remarks":'', // 备注
					"material_cost_id": 0, // ID

				},
				disabledObj:{
					"material_number_isDisabled": false,
					"material_name_isDisabled": false,
					"unit_price_of_materials_isDisabled": false,
					"unit_of_calculation_isDisabled": false,
					"remarks_isDisabled": false,
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
					bl = this.$check_action('/material_cost/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/material_cost/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/material_cost/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/material_cost/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/material_cost/view','get');
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
