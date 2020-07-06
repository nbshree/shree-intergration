import Vue from 'vue';
import VueRouter from 'vue-router';
import Layout from '@/views/layout/index';
// import _import from '@/utils/import';

// const Layout = require('@/views/layout/index');

Vue.use(VueRouter);

export const constantRouterMap = [
    // {
    //   path: '/redirect',
    //   component: Layout,
    //   hidden: true,
    //   children: [
    //     {
    //       path: '/redirect/:path*',
    //       component: _import('redirect/index')
    //     }
    //   ]
    // },
    {
        path: '',
        component: Layout,
        redirect: 'ajaxIndex',
        single: true,
        children: [
            {
                path: 'ajaxIndex',
                name: 'ajaxIndex',
                component: () => import('@/views/testView/ajaxIndex'),
                meta: {
                    title: '控制面板test',
                    icon: 'ajaxIndex',
                    permission: 'ajaxIndex'
                }
            }
        ]
    },
    {
        path: '/login',
        component: () => import('@/views/login/index'),
        hidden: true
    },
    {
        path: '/403',
        component: () => import('@/views/errorPage/403'),
        hidden: true
    },
    {
        path: '/404',
        component: () => import('@/views/errorPage/404'),
        hidden: true
    },
    {
        path: '/500',
        component: () => import('@/views/errorPage/500'),
        hidden: true
    },
    {
        path: '/projectList',
        component: () => import('@/views/project/projectList'),
        hidden: true
    },
    {
        path: '/iconTest',
        component: () => import('@/views/testView/iconTest'),
        hidden: true
    },
    // {
    //     path: '/ajaxIndex',
    //     component: () => import('@/views/testView/ajaxIndex'),
    //     hidden: true
    // },
    {
        path: '/test1',
        component: () => import('@/views/testView/test1'),
        hidden: true,
        children: [
            {
                path: "",
                // component:() => import('@/views/testView/test2')
                components: {
                    test1: () => import('@/views/testView/test2'),
                    default: () => import('@/views/testView/test1'), //默认省略不写name的情况
                }
            }
        ]
    },
    // {
    //   path: '/lock',
    //   component: _import('lock/index'),
    //   name: 'lock',
    //   hidden: true
    // },
];

const createRouter = () =>
    new VueRouter({
        // mode: 'history', // require service support
        // scrollBehavior: () => ({ y: 0 }),
        routes: constantRouterMap
    });

const router = createRouter();
//
// export function resetRouter () {
//   const newRouter = createRouter()
//   router.matcher = newRouter.matcher // reset router
// }

export default router;
