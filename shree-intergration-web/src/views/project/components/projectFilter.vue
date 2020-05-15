<template>
    <a-modal v-model="visible" width="800px" title="筛选条件">
        <a-form-model :model="project" :label-col="labelCol" :wrapper-col="wrapperCol">
            <a-form-model-item label="项目编号">
                <a-input v-model="project.projectNo"/>
            </a-form-model-item>
            <a-form-model-item label="项目名称">
                <a-input v-model="project.projectName"/>
            </a-form-model-item>
            <a-form-model-item label="业务部门">
                <a-select v-model="project.bizDeptName" placeholder="please select your zone">
                    <a-select-option value="shanghai">
                        Zone one
                    </a-select-option>
                    <a-select-option value="beijing">
                        Zone two
                    </a-select-option>
                </a-select>
            </a-form-model-item>
            <a-form-model-item label="开始时间">
                <a-date-picker
                        v-model="project.planStartTime"
                        show-time
                        type="date"
                        placeholder="Pick a date"
                        style="width: 100%;"
                />
            </a-form-model-item>
            <a-form-model-item label="状态">
                <a-switch v-model="project.status"/>
            </a-form-model-item>
            <a-form-model-item label="技术人员">
                <a-select v-model="project.techManagerName" placeholder="please select your zone">
                    <a-select-option value="shanghai">
                        Zone one
                    </a-select-option>
                    <a-select-option value="beijing">
                        Zone two
                    </a-select-option>
                </a-select>
            </a-form-model-item>
            <a-form-model-item label="备注">
                <a-input v-model="project.remark" type="textarea"/>
            </a-form-model-item>
        </a-form-model>
        <template slot="footer">
            <a-button key="close" @click="onClose">
                取消
            </a-button>
            <a-button key="submit" type="primary" :loading="loading" @click="onSubmit">
                查询
            </a-button>
        </template>
    </a-modal>
</template>
<script>
    import moment from 'moment';

    const projectData = {
        id: "",
        status: "",
        projectNo: "",
        projectName: "",
        bizDeptName: "",
        bizManagerName: "",
        techDeptName: "",
        techManagerName: [],
        planStartTime: null,
        planEndTime: null,
        remark: "",
    };

    export default {
        data() {
            return {
                visible: false,
                loading: false,
                labelCol: {span: 4},
                wrapperCol: {span: 14},
                project: projectData,
            };
        },
        methods: {
            moment,

            onSubmit() {
                this.handleClosed();
            },
            onOpen() {
                this.visible = true;
            },
            onClose() {
                this.visible = false;
                this.handleClosed();
            },
            handleClosed() {
                this.$emit("onClosed");
            }
        },
    };
</script>