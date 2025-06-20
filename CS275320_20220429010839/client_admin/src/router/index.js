import Vue from 'vue';
import VueRouter from 'vue-router';
import index from '../views/index.vue';
import login from '../views/login.vue';
import forgot from '../views/forgot.vue';
Vue.use(VueRouter)

const routes = [
	// 主页
	{
		path: '/',
		name: 'index',
		component: index,
		meta: {
			index: 0,
			title: '首页'
		}
	},

	// 登录
	{
		path: '/login',
		name: 'login',
		component: login,
		meta: {
			index: 0,
			title: '登录'
		}
	},

	// 忘记密码
	{
		path: '/forgot',
		name: "forgot",
		component: forgot,
		meta: {
			index: 0,
			title: '忘记密码'
		}
	},

	// 修改密码
	{
		path: '/user/password',
		name: "password",
		component: () => import("../views/user/password.vue"),
		meta: {
			index: 0,
			title: '修改密码'
		}
	},

	// 视频播放页
	{
		path: "/media/video",
		name: "video",
		component: () => import('../views/media/video.vue'),
		meta: {
			index: 0,
			title: "视频"
		}
	},

	// 音频播放页
	{
		path: "/media/audio",
		name: "audio",
		component: () => import('../views/media/audio.vue'),
		meta: {
			index: 0,
			title: "音频"
		}
	},
	// 员工路由
	{
		path: '/staff/table',
		name: 'staff_table',
		component: () => import('../views/staff/table.vue')
	},
	{
		path: '/staff/view',
		name: 'staff_view',
		component: () => import('../views/staff/view.vue')
	},
	// 菜谱管理路由
	{
		path: '/menu_management/table',
		name: 'menu_management_table',
		component: () => import('../views/menu_management/table.vue')
	},
	{
		path: '/menu_management/view',
		name: 'menu_management_view',
		component: () => import('../views/menu_management/view.vue')
	},
	// 材料成本路由
	{
		path: '/material_cost/table',
		name: 'material_cost_table',
		component: () => import('../views/material_cost/table.vue')
	},
	{
		path: '/material_cost/view',
		name: 'material_cost_view',
		component: () => import('../views/material_cost/view.vue')
	},
	// 座位信息路由
	{
		path: '/seat_information/table',
		name: 'seat_information_table',
		component: () => import('../views/seat_information/table.vue')
	},
	{
		path: '/seat_information/view',
		name: 'seat_information_view',
		component: () => import('../views/seat_information/view.vue')
	},
	// 开台信息路由
	{
		path: '/opening_information/table',
		name: 'opening_information_table',
		component: () => import('../views/opening_information/table.vue')
	},
	{
		path: '/opening_information/view',
		name: 'opening_information_view',
		component: () => import('../views/opening_information/view.vue')
	},
	// 点餐订单路由
	{
		path: '/ordering_order/table',
		name: 'ordering_order_table',
		component: () => import('../views/ordering_order/table.vue')
	},
	{
		path: '/ordering_order/view',
		name: 'ordering_order_view',
		component: () => import('../views/ordering_order/view.vue')
	},
	// 结账管理路由
	{
		path: '/settlement_management/table',
		name: 'settlement_management_table',
		component: () => import('../views/settlement_management/table.vue')
	},
	{
		path: '/settlement_management/view',
		name: 'settlement_management_view',
		component: () => import('../views/settlement_management/view.vue')
	},
	// 统计报表路由
	{
		path: '/statistical_report/table',
		name: 'statistical_report_table',
		component: () => import('../views/statistical_report/table.vue')
	},
	{
		path: '/statistical_report/view',
		name: 'statistical_report_view',
		component: () => import('../views/statistical_report/view.vue')
	},
	// 菜品类别路由
	{
		path: '/dish_category/table',
		name: 'dish_category_table',
		component: () => import('../views/dish_category/table.vue')
	},
	{
		path: '/dish_category/view',
		name: 'dish_category_view',
		component: () => import('../views/dish_category/view.vue')
	},

	// 用户路由
	{
		path: '/user/table',
		name: 'user_table',
		component: () => import('../views/user/table.vue'),
		meta: {
			index: 0,
			title: '用户列表'
		}
	},
	{
		path: '/user/view',
		name: 'user_view',
		component: () => import('../views/user/view.vue'),
		meta: {
			index: 0,
			title: '用户详情'
		}
	},
	{
		path: '/user/info',
		name: 'user_info',
		component: () => import('../views/user/info.vue'),
		meta: {
			index: 0,
			title: '个人信息'
		}
	},
	// 用户组路由
	{
		path: '/user_group/table',
		name: 'user_group_table',
		component: () => import('../views/user_group/table.vue'),
		meta: {
			index: 0,
			title: '用户组列表'
		}
	},
	{
		path: '/user_group/view',
		name: 'user_group_view',
		component: () => import('../views/user_group/view.vue'),
		meta: {
			index: 0,
			title: '用户组详情'
		}
	}
]

const router = new VueRouter({
	mode: 'history',
	base: process.env.BASE_URL,
	routes
})

router.beforeEach((to, from, next) => {
	let token = to.query.token;
	if (token){
		$.db.set("token",token,120);
	}
	next();
})

router.afterEach((to, from, next) => {
	let title = "餐饮管理系统-admin";
	document.title = title;
})

export default router
