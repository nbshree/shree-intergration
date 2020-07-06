<template>
    <a-form-model
            id="components-form-demo-normal-login"
            :model="formInline"
            class="login-form"
            @submit="handleSubmit"
            ref="ruleForm"
            :rules="rules"
    >
        <a-form-model-item prop="user">
            <a-input v-model="formInline.user" placeholder="Username">
                <a-icon slot="prefix" type="user" style="color: rgba(0,0,0,.25)"/>
            </a-input>
        </a-form-model-item>
        <a-form-model-item prop="password">
            <a-input
                    v-model="formInline.password"
                    type="password"
                    placeholder="Password"
            >
                <a-icon slot="prefix" type="lock" style="color: rgba(0,0,0,.25)"/>
            </a-input>
        </a-form-model-item>
        <a-form-model-item>
            <a-checkbox
                    v-model="formInline.remember"
            >
                Remember me
            </a-checkbox>
            <a class="login-form-forgot" @click="goForgot">
                Forgot password
            </a>
            <a-button type="primary" html-type="submit" class="login-form-button">
                Log in
            </a-button>
            Or
            <a href="">
                register now!
            </a>
        </a-form-model-item>
    </a-form-model>
</template>

<script>
    export default {
        data() {
            return {
                formInline: {
                    user: 'admin',
                    password: 'qwe123',
                    remember: false
                },
                rules: {
                    user: [{required: true, message: '必填', trigger: 'blur'}],
                    password: [{required: true, message: '必填', trigger: 'blur'}],
                }
            }
        },
        methods: {
            handleSubmit(e) {
                // e.preventDefault();
                this.$refs["ruleForm"].validate(valid => {
                    if (valid) {
                        this.$emit("login", this.formInline);
                    } else {
                        this.$message.error('请填写正确的信息');
                    }
                });
            },
            resetForm() {
                this.$refs["ruleForm"].resetFields();
            },
            goForgot() {
                this.$emit("goForgot");
            }
        },
    };
</script>
<style lang="less" scoped>
    #components-form-demo-normal-login {
        login-form {
            max-width: 300px;
        }

        .login-form-forgot {
            float: right
        }

        .login-form-button {
            width: 100%;
        }
    }
</style>
