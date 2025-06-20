<template>
	<el-main class="bg edit_wrap">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','seat_number') || $check_field('add','seat_number') || $check_field('set','seat_number')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="座位号" prop="seat_number">
					<el-input id="seat_number" v-model="form['seat_number']" placeholder="请输入座位号"
							  v-if="user_group === '管理员' || (form['seat_information_id'] && $check_field('set','seat_number')) || (!form['seat_information_id'] && $check_field('add','seat_number'))" :disabled="disabledObj['seat_number_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','seat_number')">{{form['seat_number']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','seat_location') || $check_field('add','seat_location') || $check_field('set','seat_location')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="座位所在" prop="seat_location">
					<el-input id="seat_location" v-model="form['seat_location']" placeholder="请输入座位所在"
							  v-if="user_group === '管理员' || (form['seat_information_id'] && $check_field('set','seat_location')) || (!form['seat_information_id'] && $check_field('add','seat_location'))" :disabled="disabledObj['seat_location_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','seat_location')">{{form['seat_location']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','capacity') || $check_field('add','capacity') || $check_field('set','capacity')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="容纳人数" prop="capacity">
					<el-input id="capacity" v-model="form['capacity']" placeholder="请输入容纳人数"
							  v-if="user_group === '管理员' || (form['seat_information_id'] && $check_field('set','capacity')) || (!form['seat_information_id'] && $check_field('add','capacity'))" :disabled="disabledObj['capacity_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','capacity')">{{form['capacity']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','seat_status') || $check_field('add','seat_status') || $check_field('set','seat_status')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="座位状态" prop="seat_status">
					<el-select id="seat_status" v-model="form['seat_status']"
						v-if="user_group === '管理员' || (form['seat_information_id'] && $check_field('set','seat_status')) || (!form['seat_information_id'] && $check_field('add','seat_status'))">
						<el-option v-for="o in list_seat_status" :key="o" :label="o" :value="o">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','seat_status')">{{form['seat_status']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','remarks') || $check_field('add','remarks') || $check_field('set','remarks')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="备注" prop="remarks">
					<el-input type="textarea" id="remarks" v-model="form['remarks']" placeholder="请输入备注"
						v-if="user_group === '管理员' || (form['seat_information_id'] && $check_field('set','remarks')) || (!form['seat_information_id'] && $check_field('add','remarks'))" :disabled="disabledObj['remarks_isDisabled']"></el-input>
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
				field: "seat_information_id",
				url_add: "~/api/seat_information/add?",
				url_set: "~/api/seat_information/set?",
				url_get_obj: "~/api/seat_information/get_obj?",
				url_upload: "~/api/seat_information/upload?",

				query: {
					"seat_information_id": 0,
				},

				form: {
					"seat_number":'', // 座位号
					"seat_location":'', // 座位所在
					"capacity":'', // 容纳人数
					"seat_status":'', // 座位状态
					"remarks":'', // 备注
					"seat_information_id": 0, // ID

				},
				disabledObj:{
					"seat_number_isDisabled": false,
					"seat_location_isDisabled": false,
					"capacity_isDisabled": false,
					"seat_status_isDisabled": false,
					"remarks_isDisabled": false,
				},
				//座位状态选项列表
				list_seat_status: ['使用中','空置'],
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
					bl = this.$check_action('/seat_information/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/seat_information/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/seat_information/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/seat_information/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/seat_information/view','get');
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
