<template>
    <a-modal v-model="visible" width="800px" :title="123" :maskClosable="false" :destroyOnClose="true">
        <a-form-model :model="project" :rules="rules" ref="ruleForm" :label-col="labelCol" :wrapper-col="wrapperCol">
            <a-form-model-item label="主键" style="display: none">
                <a-input v-model="project.id"/>
            </a-form-model-item>
            <a-form-model-item label="项目编号" prop="projectNo">
                <a-input v-model="project.projectNo"/>
            </a-form-model-item>
            <a-form-model-item label="项目名称" prop="projectName">
                <a-input v-model="project.projectName"/>
            </a-form-model-item>
            <a-form-model-item label="业务负责" layout="inline">
                <a-row>
                    <a-col :span="11">
                        <a-select v-model="project.bizDeptName" placeholder="请选择小分队">
                            <a-select-option v-for="item in orgTypesCache" :key="item.dictCode"
                                             :value="item.dictName">{{ item.dictName }}
                            </a-select-option>
                        </a-select>
                    </a-col>
                    <a-col :span="2" style="text-align: center"></a-col>
                    <a-col :span="11">
                        <a-select v-model="project.bizManagerName" placeholder="请选择成员">
                            <a-select-option v-for="item in orgTypesCache" :key="item.dictCode"
                                             :value="item.dictName">{{ item.dictName }}
                            </a-select-option>
                        </a-select>
                    </a-col>
                </a-row>
            </a-form-model-item>
            <a-form-model-item label="技术负责">
                <a-row>
                    <a-col :span="11">
                        <a-select v-model="project.techDeptName" placeholder="请选择小分队">
                            <a-select-option v-for="item in orgTypesCache" :key="item.dictCode"
                                             :value="item.dictName">{{ item.dictName }}
                            </a-select-option>
                        </a-select>
                    </a-col>
                    <a-col :span="2" style="text-align: center"></a-col>
                    <a-col :span="11">
                        <a-select v-model="project.techManagerName" placeholder="请选择成员">
                            <a-select-option v-for="item in orgTypesCache" :key="item.dictCode"
                                             :value="item.dictName">{{ item.dictName }}
                            </a-select-option>
                        </a-select>
                    </a-col>
                </a-row>
            </a-form-model-item>
            <a-form-model-item label="计划时间">
                <a-row>
                    <a-col :span="11">
                        <a-date-picker style="width: 100%" v-model="project.planStartTime" show-Time
                                       :disabled-date="onDisabledStartDate" format="YYYY-MM-DD"
                                       placeholder="开始时间" @openChange="onPlanTimeStartChange"></a-date-picker>
                    </a-col>
                    <a-col :span="2" style="text-align: center">~</a-col>
                    <a-col :span="11">
                        <a-date-picker style="width: 100%" v-model="project.planEndTime" show-Time
                                       :disabled-date="onDisabledEndDate" format="YYYY-MM-DD" placeholder="结束时间"
                                       :open="isPlanTimeOpen" @openChange="onPlanTimeEndChange"></a-date-picker>
                    </a-col>
                </a-row>
            </a-form-model-item>
            <a-form-model-item label="计划工时/天">
                <a-input v-model="project.planWorkCost"/>
            </a-form-model-item>
            <a-form-model-item label="备注">
                <a-input v-model="project.remark" type="textarea" style="height: 120px"/>
            </a-form-model-item>
        </a-form-model>
    </a-modal>

</template>
<script>
    import moment from 'moment';
    import 'moment/locale/zh-cn';
    // import httpAjax from "@/utils/httpAjax";
    // import dictCache from "@/utils/dictCache";

    const bizDeptData = [
        {key: '1', name: '市场部'},
        {key: '2', name: '政企部'}
    ];

    const projectData = {
        id: "",
        status: "",
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
        planWorkCost: "",
        remark: "",
    };

    export default {
        props: {
            relationId: String,
            mode: String,
        },
        data() {
            return {
                visible: false,
                isPlanTimeOpen: false,
                loading: false,
                labelCol: {span: 4},
                wrapperCol: {span: 14},
                orgTypesCache: [],
                project: projectData,
                bizDeptItems: bizDeptData,
                rules: {
                    projectNo: [{required: true, message: '请输入项目编号', trigger: 'blur'}],
                    projectName: [{required: true, message: '请输入项目名称', trigger: 'blur'}],
                },
            };
        },
        mounted() {
            this.project.id = this.relationId;
            if (this.mode === "edit" || this.mode === "view") {
                this.onOpen(this.project.id);
            }
            // this.getOrgTypes();
        },
        methods: {
            moment,
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
                // this.$refs.ruleForm.validate(valid => {
                //     if (valid) {
                //         httpAjax.post('api/pm/project/save', params)
                //             .then((result) => {
                //                 if (result.status === 1) {
                //                     this.$message.success("数据保存成功");
                //                     this.nextStep(result.data);
                //                 } else {
                //                     this.$error({
                //                         title: "系统错误",
                //                         content: result.message,
                //                     });
                //                 }
                //             })
                //             .catch(() => {
                //             });
                //     } else {
                //         console.log('error submit!!');
                //         return false;
                //     }
                // });
            },
            nextStep(data) {
                this.$emit('nextStep', data)
            },
            consoleMe(e){
                console.log(e)
                this.visible=true
            }
            // getOrgTypes() {
            //     dictCache.getDict('ORG_TYPE').then(result => {
            //         this.orgTypesCache = result.getItems()
            //     });
            // },
        },
    };
</script>
