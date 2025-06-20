<template>
	<el-main class="bg edit_wrap">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','order_number') || $check_field('add','order_number') || $check_field('set','order_number')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="订单号" prop="order_number">
					<el-input id="order_number" v-model="form['order_number']" placeholder="请输入订单号"
							  v-if="user_group === '管理员' || (form['ordering_order_id'] && $check_field('set','order_number')) || (!form['ordering_order_id'] && $check_field('add','order_number'))" :disabled="disabledObj['order_number_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','order_number')">{{form['order_number']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','seat_number') || $check_field('add','seat_number') || $check_field('set','seat_number')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="座位号" prop="seat_number">
					<el-input id="seat_number" v-model="form['seat_number']" placeholder="请输入座位号"
							  v-if="user_group === '管理员' || (form['ordering_order_id'] && $check_field('set','seat_number')) || (!form['ordering_order_id'] && $check_field('add','seat_number'))" :disabled="disabledObj['seat_number_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','seat_number')">{{form['seat_number']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','dish_name') || $check_field('add','dish_name') || $check_field('set','dish_name')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="菜品名称" prop="dish_name">
					<el-select id="dish_name" v-model="form['dish_name']"
						v-if="user_group === '管理员' || (form['ordering_order_id'] && $check_field('set','dish_name')) || (!form['ordering_order_id'] && $check_field('add','dish_name'))">
						<el-option v-for="o in list_dish_name" :key="o['dish_information']" :label="o['dish_information']"
							:value="o['dish_information']">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','dish_name')">{{form['dish_name']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','quantity_of_dishes') || $check_field('add','quantity_of_dishes') || $check_field('set','quantity_of_dishes')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="菜品数量" prop="quantity_of_dishes">
					<el-input id="quantity_of_dishes" v-model="form['quantity_of_dishes']" placeholder="请输入菜品数量"
							  v-if="user_group === '管理员' || (form['ordering_order_id'] && $check_field('set','quantity_of_dishes')) || (!form['ordering_order_id'] && $check_field('add','quantity_of_dishes'))" :disabled="disabledObj['quantity_of_dishes_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','quantity_of_dishes')">{{form['quantity_of_dishes']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','dish_price') || $check_field('add','dish_price') || $check_field('set','dish_price')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="菜品价格" prop="dish_price">
					<el-input-number id="dish_price" v-model.number="form['dish_price']"
						v-if="user_group === '管理员' || (form['ordering_order_id'] && $check_field('set','dish_price')) || (!form['ordering_order_id'] && $check_field('add','dish_price'))" :disabled="disabledObj['dish_price_isDisabled']"></el-input-number>
					<div v-else-if="$check_field('get','dish_price')">{{form['dish_price']}}</div>
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
				field: "ordering_order_id",
				url_add: "~/api/ordering_order/add?",
				url_set: "~/api/ordering_order/set?",
				url_get_obj: "~/api/ordering_order/get_obj?",
				url_upload: "~/api/ordering_order/upload?",

				query: {
					"ordering_order_id": 0,
				},

				form: {
					"order_number":'', // 订单号
					"seat_number":'', // 座位号
					"dish_name":'', // 菜品名称
					"quantity_of_dishes":'', // 菜品数量
					"dish_price":0, // 菜品价格
					"ordering_order_id": 0, // ID

				},
				disabledObj:{
					"order_number_isDisabled": false,
					"seat_number_isDisabled": false,
					"dish_name_isDisabled": false,
					"quantity_of_dishes_isDisabled": false,
          "dish_price_isDisabled": false,
				},
				//菜品名称选项列表
				list_dish_name: [],
			}
		},
		methods: {
			/**
			 * 获取菜品名称列表
			 */
			async get_list_dish_name() {
				var json = await this.$get("~/api/menu_management/get_list?");
				if(json.result && json.result.list){
					this.list_dish_name = json.result.list;
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
				// 获取缓存数据附加
				form = $.db.get("form");
				$.push(this.form ,form);
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
					bl = this.$check_action('/ordering_order/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/ordering_order/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/ordering_order/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/ordering_order/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/ordering_order/view','get');
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
			this.get_list_dish_name();
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
