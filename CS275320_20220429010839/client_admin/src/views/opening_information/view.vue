<template>
	<el-main class="bg edit_wrap">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','order_number') || $check_field('add','order_number') || $check_field('set','order_number')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="订单号" prop="order_number">
					<el-input id="order_number" v-model="form['order_number']" placeholder="请输入订单号"
							  v-if="user_group === '管理员' || (form['opening_information_id'] && $check_field('set','order_number')) || (!form['opening_information_id'] && $check_field('add','order_number'))" :disabled="true"></el-input>
					<div v-else-if="$check_field('get','order_number')">{{form['order_number']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','seat_number') || $check_field('add','seat_number') || $check_field('set','seat_number')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="座位号" prop="seat_number">
					<el-input id="seat_number" v-model="form['seat_number']" placeholder="请输入座位号"
							  v-if="user_group === '管理员' || (form['opening_information_id'] && $check_field('set','seat_number')) || (!form['opening_information_id'] && $check_field('add','seat_number'))" :disabled="disabledObj['seat_number_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','seat_number')">{{form['seat_number']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','opening_time') || $check_field('add','opening_time') || $check_field('set','opening_time')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="开台时间" prop="opening_time">
					<el-date-picker :disabled="disabledObj['opening_time_isDisabled']" v-if="user_group === '管理员' || (form['opening_information_id'] && $check_field('set','opening_time')) || (!form['opening_information_id'] && $check_field('add','opening_time'))" id="opening_time"
						v-model="form['opening_time']" type="date" placeholder="选择日期">
					</el-date-picker>
					<div v-else-if="$check_field('get','opening_time')">{{form['opening_time']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','number_of_people') || $check_field('add','number_of_people') || $check_field('set','number_of_people')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="人数" prop="number_of_people">
					<el-input id="number_of_people" v-model="form['number_of_people']" placeholder="请输入人数"
							  v-if="user_group === '管理员' || (form['opening_information_id'] && $check_field('set','number_of_people')) || (!form['opening_information_id'] && $check_field('add','number_of_people'))" :disabled="disabledObj['number_of_people_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','number_of_people')">{{form['number_of_people']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','tea_fee') || $check_field('add','tea_fee') || $check_field('set','tea_fee')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="茶位费" prop="tea_fee">
					<el-input-number id="tea_fee" v-model.number="form['tea_fee']"
						v-if="user_group === '管理员' || (form['opening_information_id'] && $check_field('set','tea_fee')) || (!form['opening_information_id'] && $check_field('add','tea_fee'))" :disabled="disabledObj['tea_fee_isDisabled']"></el-input-number>
					<div v-else-if="$check_field('get','tea_fee')">{{form['tea_fee']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','consumption_amount') || $check_field('add','consumption_amount') || $check_field('set','consumption_amount')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="消费金额" prop="consumption_amount">
					<el-input-number id="consumption_amount" v-model.number="form['consumption_amount']"
						v-if="user_group === '管理员' || (form['opening_information_id'] && $check_field('set','consumption_amount')) || (!form['opening_information_id'] && $check_field('add','consumption_amount'))" :disabled="disabledObj['consumption_amount_isDisabled']"></el-input-number>
					<div v-else-if="$check_field('get','consumption_amount')">{{form['consumption_amount']}}</div>
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
				field: "opening_information_id",
				url_add: "~/api/opening_information/add?",
				url_set: "~/api/opening_information/set?",
				url_get_obj: "~/api/opening_information/get_obj?",
				url_upload: "~/api/opening_information/upload?",

				query: {
					"opening_information_id": 0,
				},

				form: {
					"order_number":this.$get_stamp(), // 订单号
					"seat_number":'', // 座位号
					"opening_time":'', // 开台时间
					"number_of_people":'', // 人数
					"tea_fee":0, // 茶位费
					"consumption_amount":0, // 消费金额
					"opening_information_id": 0, // ID

				},
				disabledObj:{
					"order_number_isDisabled": false,
					"seat_number_isDisabled": false,
					"opening_time_isDisabled": false,
					"number_of_people_isDisabled": false,
          "tea_fee_isDisabled": false,
          "consumption_amount_isDisabled": false,
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
        if (this.form["opening_time"].indexOf("-")===-1){
          this.form["opening_time"] = this.$toTime(parseInt(this.form["opening_time"]),"yyyy-MM-dd")
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
        if(this.form["opening_time"]=="0000-00-00"){
          this.form["opening_time"] = null;
        }
				if(parseInt(this.form["opening_time"]) > 9999){
					this.form["opening_time"] = this.$toTime(parseInt(this.form["opening_time"]),"yyyy-MM-dd")
				}
			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/opening_information/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/opening_information/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/opening_information/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/opening_information/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/opening_information/view','get');
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
