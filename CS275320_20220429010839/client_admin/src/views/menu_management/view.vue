<template>
	<el-main class="bg edit_wrap">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','dish_no') || $check_field('add','dish_no') || $check_field('set','dish_no')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="菜品编号" prop="dish_no">
					<el-input id="dish_no" v-model="form['dish_no']" placeholder="请输入菜品编号"
							  v-if="user_group === '管理员' || (form['menu_management_id'] && $check_field('set','dish_no')) || (!form['menu_management_id'] && $check_field('add','dish_no'))" :disabled="disabledObj['dish_no_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','dish_no')">{{form['dish_no']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','dish_information') || $check_field('add','dish_information') || $check_field('set','dish_information')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="菜品信息" prop="dish_information">
					<el-input id="dish_information" v-model="form['dish_information']" placeholder="请输入菜品信息"
							  v-if="user_group === '管理员' || (form['menu_management_id'] && $check_field('set','dish_information')) || (!form['menu_management_id'] && $check_field('add','dish_information'))" :disabled="disabledObj['dish_information_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','dish_information')">{{form['dish_information']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','dish_category') || $check_field('add','dish_category') || $check_field('set','dish_category')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="菜品类别" prop="dish_category">
					<el-select id="dish_category" v-model="form['dish_category']"
						v-if="user_group === '管理员' || (form['menu_management_id'] && $check_field('set','dish_category')) || (!form['menu_management_id'] && $check_field('add','dish_category'))">
						<el-option v-for="o in list_dish_category" :key="o['dish_category']" :label="o['dish_category']"
							:value="o['dish_category']">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','dish_category')">{{form['dish_category']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','remarks') || $check_field('add','remarks') || $check_field('set','remarks')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="备注" prop="remarks">
					<el-input type="textarea" id="remarks" v-model="form['remarks']" placeholder="请输入备注"
						v-if="user_group === '管理员' || (form['menu_management_id'] && $check_field('set','remarks')) || (!form['menu_management_id'] && $check_field('add','remarks'))" :disabled="disabledObj['remarks_isDisabled']"></el-input>
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
				field: "menu_management_id",
				url_add: "~/api/menu_management/add?",
				url_set: "~/api/menu_management/set?",
				url_get_obj: "~/api/menu_management/get_obj?",
				url_upload: "~/api/menu_management/upload?",

				query: {
					"menu_management_id": 0,
				},

				form: {
					"dish_no":'', // 菜品编号
					"dish_information":'', // 菜品信息
					"dish_category":'', // 菜品类别
					"remarks":'', // 备注
					"menu_management_id": 0, // ID

				},
				disabledObj:{
					"dish_no_isDisabled": false,
					"dish_information_isDisabled": false,
					"dish_category_isDisabled": false,
					"remarks_isDisabled": false,
				},
				//菜品类别选项列表
				list_dish_category: [],
			}
		},
		methods: {
			/**
			 * 获取菜品类别列表
			 */
			async get_list_dish_category() {
				var json = await this.$get("~/api/dish_category/get_list?");
				if(json.result && json.result.list){
					this.list_dish_category = json.result.list;
				}
				else if(json.error){
					console.error(json.error);
				}
			},

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
					bl = this.$check_action('/menu_management/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/menu_management/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/menu_management/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/menu_management/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/menu_management/view','get');
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
			this.get_list_dish_category();
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
