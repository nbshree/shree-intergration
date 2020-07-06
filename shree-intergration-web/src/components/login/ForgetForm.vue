<template>
    <a-form-model ref="ruleForm" :model="ruleForm" :rules="rules" v-bind="layout">
        <a-form-model-item has-feedback prop="email">
            <a-input v-model="ruleForm.email" placeholder="请输入邮箱" type="text" autocomplete="off"/>
        </a-form-model-item>
        <a-form-model-item has-feedback prop="verification">
            <a-input v-model="ruleForm.verification" placeholder="请输入验证码" type="text" autocomplete="off"/>
        </a-form-model-item>
        <a-form-model-item has-feedback prop="password">
            <a-input v-model.number="ruleForm.password" type="password" placeholder="请输入新密码" autocomplete="off"/>
        </a-form-model-item>
        <a-form-model-item has-feedback prop="passwordRepeat">
            <a-input v-model.number="ruleForm.passwordRepeat" type="password" placeholder="请输入确认密码" autocomplete="off"/>
        </a-form-model-item>
        <a-form-model-item :wrapper-col="{ span: 24, offset: 0 }" style="text-align: center">
            <a-col :span="8">
                <a-button @click="back">
                    返回
                </a-button>
            </a-col>
            <a-col :span="8">
                <a-button style="margin-left: 10px" type="primary" @click="submitForm('ruleForm')">
                    提交
                </a-button>
            </a-col>
            <a-col :span="8">
                <a-button style="margin-left: 10px" @click="resetForm('ruleForm')">
                    重置
                </a-button>
            </a-col>
        </a-form-model-item>
    </a-form-model>
</template>
<script>
    export default {
        data() {
            let checkEmail = (rule, value, callback) => {
                let reg = new RegExp("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$");
                if (!value) {
                    return callback(new Error('输入不能为空'));
                }
                if (!reg.test(value)) {
                    callback(new Error('邮箱格式不正确'));
                } else {
                    callback();
                }
            };
            let checkVerification = (rule, value, callback) => {
                if (!value) {
                    return callback(new Error('输入不能为空'));
                } else {
                    callback();
                }
            };
            let validatePassword = (rule, value, callback) => {
                let valString = value.toString();
                if (valString === '') {
                    callback(new Error('输入不能为空'));
                } else if (valString.length < 8 || valString.length > 16) {
                    callback(new Error('密码不能小于8位或者大于16位'));
                } else {
                    callback();
                }
            };
            let validatePasswordRepeat = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('输入不能为空'));
                } else if (value !== this.ruleForm.password) {
                    callback(new Error("输入密码不相同"));
                } else {
                    callback();
                }
            };
            return {
                ruleForm: {
                    email: '',
                    verification: '',
                    password: '',
                    passwordRepeat: '',
                },
                rules: {
                    email: [{validator: checkEmail, trigger: 'blur'}],
                    verification: [{validator: checkVerification, trigger: 'blur'}],
                    password: [{validator: validatePassword, trigger: 'blur'}],
                    passwordRepeat: [{validator: validatePasswordRepeat, trigger: 'blur'}],
                },
                layout: {
                    // labelCol: { span: 8 },
                    wrapperCol: {span: 24},
                },
            };
        },
        methods: {
            submitForm(formName) {
                this.$refs[formName].validate(valid => {
                    if (valid) {
                        alert('submit!');
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            },
            back(){
                this.$emit('back');
            }
        },
    };
</script>
