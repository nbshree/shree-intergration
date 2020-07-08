<template>
    <div class="login-page">
        <div class="login-wrap">
            <a-col :class="translateLeft" :span="10">
                <div v-show="notforget" class="login-card">
                    <a-icon style="font-size: 4rem" type="sketch"/>
                    <div class="title">
                        <span>Shree</span>
                    </div>
                    <div class="loginForm">
                        <login-form ref="login-form" @goForgot="wrapSwitch(false)" @login="loginUser"></login-form>
                    </div>
                </div>
                <div v-show="!notforget" class="login-card-right">
                    <div class="title">
                        <span>Shree</span>
                    </div>
                    <div class="loginForm">
                        <forget-form @back="wrapSwitch(true)"></forget-form>
                    </div>
                </div>
            </a-col>
            <a-col :class="translateRight" :span="14">
                <div class="wallpaper"></div>
            </a-col>
        </div>
    </div>
</template>
<script>
    import {mapState, mapGetters, mapMutations, mapActions} from "vuex";

    import httpAjax from "@/utils/httpAjax";
    import LoginForm from "@/components/login/LoginForm";
    import ForgetForm from "@/components/login/ForgetForm";

    export default {
        components: {
            LoginForm,
            ForgetForm
        },
        data() {
            return {
                // switchLeft: true,
                // switchRight: true,
                // notforget: false,
                switchLeft: false,
                switchRight: false,
                notforget: true,
            };
        },
        computed: {
            translateLeft() {
                return {
                    'translate-left': true,
                    'login-col': true,
                    'switch-left': this.switchLeft
                }
            },
            translateRight() {
                return {
                    'translate-right': true,
                    'login-col': true,
                    'switch-right': this.switchLeft
                };
            }
        },
        methods: {
            ...mapActions("user", {"logins": "login"}),
            wrapSwitch(state) {
                this.switchLeft = !this.switchLeft;
                this.switchRight = !this.switchRight;
                setTimeout(() => {
                    this.notforget = state;
                    this.$refs['login-form'].resetForm();
                }, 300);
            },
            loginUser(data) {
                this.logins({username: data.user, password: data.password}).then((response) => {
                    if (response.status === 1) {
                        let routerPath = '/';
                        this.$router.push({path: routerPath});
                    } else {
                        this.$message.error('登陆失败！');
                    }
                }, (error) => {
                    throw new Error(error);
                });
            }
        },
    };
</script>
<style lang="less" scoped>
    .login-col {
        height: 100%;
    }

    .login-page {
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100%;
        width: 100%;
    }

    .login-wrap {
        width: 900px;
        height: 400px;
        background: white;

        .translate-left,
        .translate-right {
            will-change: auto;
            transform: translateX(0px);
            transition: transform .6s ease-in-out;
        }

        .translate-left {
            padding-top: 25px;

            .title {
                font-weight: 700;
                color: #41b883;
                padding-top: 8px;
                font-size: 22px;
            }

            .login-card {
                justify-content: center;
                align-items: center;
                display: flex;
                flex-direction: column;
            }

            .login-card-right {
                .title {
                    padding: 10px 30px 0;
                }
            }
        }

        .switch-left {
            transform: translateX(525px);
        }

        .switch-right {
            transform: translateX(-375px);
        }

        .loginForm {
            padding: 10px 30px 0;
        }

        .wallpaper {
            width: 100%;
            height: 100%;
            background: url('../../assets/images/Taylor-Swift.jpg') no-repeat;
            background-size: cover;
        }
    }
</style>
