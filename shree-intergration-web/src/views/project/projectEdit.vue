<template xmlns:a-col="http://www.w3.org/1999/html">
    <a-modal v-model="visible" width="800px" title="项目信息">
        <a-form-model :model="project" :label-col="labelCol" :wrapper-col="wrapperCol">
            <a-form-model-item label="主键" style="display: none">
                <a-input v-model="project.id"/>
            </a-form-model-item>
            <a-form-model-item label="项目编号">
                <a-input v-model="project.projectNo"/>
            </a-form-model-item>
            <a-form-model-item label="项目名称">
                <a-input v-model="project.projectName"/>
            </a-form-model-item>
            <a-form-model-item label="业务负责" layout="inline">
                <a-row>
                    <a-col :span="11">
                        <a-select v-model="project.bizDeptName" placeholder="请选择小分队">
                            <a-select-option v-for="item in bizDeptItems" :key="item.key" :value="item.name">
                                {{ item.name }}
                            </a-select-option>
                        </a-select>
                    </a-col>
                    <a-col :span="2" style="text-align: center"></a-col>
                    <a-col :span="11">
                        <a-select v-model="project.bizManagerName" placeholder="请选择成员">
                            <a-select-option v-for="item in orgTypesCache.getItems()" :key="item.dictCode"
                                             :value="item.dictName">
                                {{ item.dictName }}
                            </a-select-option>
                        </a-select>
                    </a-col>
                </a-row>
            </a-form-model-item>
            <a-form-model-item label="技术负责">
                <a-row>
                    <a-col :span="11">
                        <a-select v-model="project.techDeptName" placeholder="请选择小分队">
                            <a-select-option v-for="item in bizDeptItems" :key="item.key" :value="item.name">
                                {{ item.name }}
                            </a-select-option>
                        </a-select>
                    </a-col>
                    <a-col :span="2" style="text-align: center"></a-col>
                    <a-col :span="11">
                        <a-select v-model="project.techManagerName" placeholder="请选择成员">
                            <a-select-option v-for="item in orgTypesCache.getItems()" :key="item.dictCode"
                                             :value="item.dictName">
                                {{ item.dictName }}
                            </a-select-option>
                        </a-select>
                    </a-col>
                </a-row>
            </a-form-model-item>
            <a-form-model-item label="计划时间">
                <a-row>
                    <a-col :span="11">
                        <a-date-picker style="width: 100%" v-model="project.planStartTime" show-Time
                                       :disabled-date="onDisabledStartDate" format="YYYY-MM-DD" placeholder="开始时间"
                                       @openChange="onPlanTimeStartChange"/>
                    </a-col>
                    <a-col :span="2" style="text-align: center">~</a-col>
                    <a-col :span="11">
                        <a-date-picker style="width: 100%" v-model="project.planEndTime" show-Time
                                       :disabled-date="onDisabledEndDate" format="YYYY-MM-DD" placeholder="结束时间"
                                       :open="isPlanTimeOpen"
                                       @openChange="onPlanTimeEndChange"/>
                    </a-col>
                </a-row>
            </a-form-model-item>
            <a-form-model-item label="备注">
                <a-input v-model="project.remark" type="textarea" style="height: 120px"/>
            </a-form-model-item>
        </a-form-model>
        <template slot="footer">
            <a-button key="close" @click="onClose">
                取消
            </a-button>
            <a-button key="submit" type="primary" :loading="loading" @click="onSubmit">
                提交
            </a-button>
        </template>
    </a-modal>
</template>
<script>
    import moment from 'moment';
    import 'moment/locale/zh-cn';
    import httpAjax from "@/utils/httpAjax";
    import dictCache from "@/utils/dictCache";

    const bizDeptData = [
        {key: '1', name: '市场部'},
        {key: '2', name: '政企部'}
    ];

    const projectData = {
        id: "",
        status: "",
        //createTime: Date.now(),
        projectNo: "",
        projectName: "",
        bizDeptId: "",
        bizDeptName: "",
        bizManagerId: "",
        bizManagerName: "",
        techDeptId: "",
        techDeptName: "",
        techManagerId: "",
        techManagerName: "",
        planStartTime: null,
        planEndTime: null,
        remark: "",
    };


    export default {
        components: {},
        data() {
            return {
                isPlanTimeOpen: false,
                visible: false,
                loading: false,
                labelCol: {span: 4},
                wrapperCol: {span: 14},
                orgTypesCache: dictCache.getDict('ORG_TYPE'),
                project: projectData,
                bizDeptItems: bizDeptData,
            };
        },
        computed: {},
        methods: {
            moment,
            onChange(dates) {
                alert(dates);
            },
            onDisabledStartDate(startValue) {
                const endValue = this.project.planEndTime;
                if (!startValue || !endValue) {
                    return false;
                }
                return startValue.valueOf() > endValue.valueOf();
            },
            onDisabledEndDate(endValue) {
                const startValue = this.project.planStartTime;
                if (!endValue || !startValue) {
                    return false;
                }
                return startValue.valueOf() >= endValue.valueOf();
            },
            onPlanTimeStartChange(open) {
                if (!open) {
                    this.isPlanTimeOpen = true;
                }
            },
            onPlanTimeEndChange(open) {
                this.isPlanTimeOpen = open;
            },
            onSubmit() {
                let params = this.project;
                if (this.project.planStartTime instanceof moment) {
                    params.planStartTime = this.project.planStartTime.format('YYYY-MM-DD 00:00:00');
                }
                if (this.project.planEndTime instanceof moment) {
                    params.planEndTime = this.project.planEndTime.format('YYYY-MM-DD 00:00:00');
                }

                httpAjax.post('api/pm/project/save', params)
                    .then((result) => {
                        if (result.status == "1") {
                            this.$message.success("数据保存成功");
                            this.onClose(result);
                        } else {
                            this.$error({
                                title: "系统错误",
                                content: result.data,
                            });
                        }
                    })
                    .catch(() => {
                    });
            },
            onOpen(mode, id) {
                this.visible = true;
                switch (mode.toLowerCase()) {
                    case "add":
                        break;
                    case "edit":
                        this.project.id = id;
                        break;
                }
            },
            onClose(value) {
                this.visible = false;
                this.$emit("onClosed", value);
            },
        },
    };
</script>