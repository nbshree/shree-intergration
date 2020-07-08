<template>
    <a-layout id="components-layout-demo-side" style="min-height: 100vh">
        <a-layout-sider v-model="collapsed" @collapse="collapse" collapsible>
            <div class="logo">ILD
                <span :style="logoDisplay">国际物流</span>
            </div>
            <a-menu theme="dark"
                    :default-selected-keys="[403]"
                    :default-open-keys="['test']"
                    mode="inline"
                    @click="handleClick"
                    :selected-keys="['test',current]">
                <a-sub-menu key="desktop">
                    <span slot="title">
                        <a-icon type="desktop"/>
                        <span>工作台</span>
                    </span>
                    <a-menu-item key="myCalendar">我的日历</a-menu-item>
                    <a-menu-item key="myTask">我的任务</a-menu-item>
                    <a-menu-item key="taskPool">任务池</a-menu-item>
                </a-sub-menu>
                <a-sub-menu key="project">
                    <span slot="title">
                        <a-icon type="appstore"/>
                        <span>项目管理</span>
                    </span>
                    <a-menu-item key="projectList">项目列表</a-menu-item>
                    <a-menu-item key="taskList">任务列表</a-menu-item>
                </a-sub-menu>
                <a-sub-menu key="chart">
                    <span slot="title">
                        <a-icon type="pie-chart"/>
                        <span>统计分析</span>
                    </span>
                    <a-menu-item key="projectChart">人员情况分析</a-menu-item>
                    <a-menu-item key="projectChart">项目情况分析</a-menu-item>
                    <a-menu-item key="taskChart">任务情况分析</a-menu-item>
                </a-sub-menu>
                <a-sub-menu key="team">
                    <span slot="title">
                        <a-icon type="team"/>
                        <span>团队管理</span>
                    </span>
                    <a-menu-item key="deptManage">部门管理</a-menu-item>
                    <a-menu-item key="personManage">人员管理</a-menu-item>
                    <a-menu-item key="bizTeam">业务小分队</a-menu-item>
                    <a-menu-item key="techTeam">技术小分队</a-menu-item>
                </a-sub-menu>
                <a-sub-menu key="setting">
                    <span slot="title">
                        <a-icon type="setting"/>
                        <span>系统设置</span>
                    </span>
                    <a-menu-item key="dict">字典</a-menu-item>
                    <a-menu-item key="param">参数</a-menu-item>
                </a-sub-menu>
                <a-sub-menu key="test">
                    <span slot="title">
                        <a-icon type="setting"/>
                        <span>测试</span>
                    </span>
                    <a-menu-item key="/403">403</a-menu-item>
                    <a-menu-item key="/404">404</a-menu-item>
                    <a-menu-item key="/projectList">projectList</a-menu-item>
                    <a-menu-item key="/test/test1">test1</a-menu-item>
                    <a-menu-item key="/iconTest">iconTest</a-menu-item>
                </a-sub-menu>

            </a-menu>
        </a-layout-sider>
        <a-layout>
            <a-layout-header class="title" style="background: #ffff; padding: 0">
                项目管理系统
            </a-layout-header>
            <a-layout-content style="margin: 0 16px">
                <router-view/>
                <!--                <projectList/>-->
            </a-layout-content>
            <a-layout-footer style="text-align: center">ILD ©2018 Created by Ant UED</a-layout-footer>
        </a-layout>
    </a-layout>
</template>
<script>
    // import projectList from "../project/projectList.vue";

    export default {
        components: {
            // projectList
        },
        data() {
            return {
                logoDisplay: 'display: inline',
                collapsed: false,
                current: "403"
            };
        },
        methods: {
            collapse(collapsed) {
                if (collapsed) {
                    this.logoDisplay = "display: none";
                } else {
                    this.logoDisplay = "display: inline";
                }
            },
            handleClick(e) {
                this.current = e.key;
                this.$router.push({path: e.key})
            },
        },
    };
</script>

<style>
    #components-layout-demo-side .logo {
        height: 32px;
        --background: rgba(255, 255, 255, 0.2);
        margin: 16px;
        font-size: 24px;
        font-weight: bold;
        color: aliceblue;
        text-align: center;
    }

    #components-layout-demo-side .title {
        font-size: 24px;
        font-weight: bold;
        color: black;
    }
</style>
