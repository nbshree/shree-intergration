<template>
    <div class="sidebar-wrap">
        <div class="left-area">
            <span class="header-title">
                Shree&nbsp;<span class="subtitle">vue-admin</span>
                <span class="version-wrap">1.0.0</span>
            </span>
        </div>

        <div class="middle-area">
            <div class="middle-area__cgwrap category" @click="handleSwitchNavbar">
                <a-icon :component="shousuSvg"/>
                <!--                <icon class="category-icon" name="category" :scale="2.8"></icon>-->
            </div>
            <p>13212</p>

            <div class="middle-area__msglist">
                <breadcrumb/>
            </div>
        </div>

        <div class="right-area">
            <!--            <lang-select class="lang" />-->
            <!--            <notice />-->
                        <fullscreen class="fullscreen" />
            <!--            <infodrop />-->
        </div>
    </div>
</template>

<script>
    import breadcrumb from '@/components/breadcrumb';
    import fullscreen from '@/components/fullscreen';
    import {shousuSvg} from '@/assets/svg/svg';
    import {mapActions} from "vuex";

    export default {
        name: 'navbar',
        components: {
            breadcrumb,
            fullscreen
            // fullscreen,
            // notice,
            // infodrop,
            // breadcrumb,
            // 'lang-select': langselect //,
            //"seamless-list": seamlessList
        },
        mounted() {
            // if (!this.$store.getters.sidebar.sliderState) {
            //     this.$store.dispatch('setSliderState', 'full');
            // }
        },
        data() {
            return {
                shousuSvg
                // system_version: this.$store.state.app.version
            };
        },
        computed: {
            // messageList: function() {
            //     return [
            //         {
            //             date: dayjs().format('YYYY-MM-DD'),
            //             msg: this.$t('msg.msg1')
            //         },
            //         {
            //             date: dayjs().format('YYYY-MM-DD'),
            //             msg: this.$t('msg.msg2')
            //         },
            //         {
            //             date: dayjs().format('YYYY-MM-DD'),
            //             msg: this.$t('msg.msg3')
            //         }
            //     ];
            // }
        },
        methods: {
            ...mapActions('app',['setSliderState']),
            handleSwitchNavbar() {
                this.setSliderState();
            }
        }
    };
</script>

<style lang="less" scoped>
    .sidebar-wrap {
        display: flex;
        height: 60px;
        padding: 0;
        font-size: 0;
        line-height: 60px;
        background: #fff;
        -webkit-box-sizing: border-box;
        box-sizing: border-box;
        overflow: hidden;

        .left-area {
            min-width: 240px;
            padding-left: 12px;
            -webkit-box-sizing: border-box;
            box-sizing: border-box;

            .header-title {
                color: #41b883;
                font-size: 20px;
                font-weight: 700;

                .subtitle {
                    color: #2e5273;
                }

                .version-wrap {
                    background: #eaeaea;
                    padding: 3px 8px;
                    font-size: 12px;
                    margin-left: 8px;
                    border-radius: 3px;
                    vertical-align: top;
                }
            }
        }

        .middle-area {
            height: 60px;
            -webkit-box-flex: 1;
            -ms-flex: 1;
            flex: 1;
            line-height: normal;
            .category{
                height: 100%;
                font-size: 32px;
                color: #41b883;
                display: inline-block;
                line-height: 60px;
                padding: 0 20px;
                cursor:pointer;
                float: left;
            }
            .middle-area__msglist{
                float: left;
                height: 100%;
                display: flex;
                flex-direction: column;
                justify-content: center;
            }
        }

        .right-area {
            display: -webkit-box;
            display: -ms-flexbox;
            display: flex;
            -webkit-box-align: center;
            -ms-flex-align: center;
            align-items: center;
            -webkit-box-pack: end;
            -ms-flex-pack: end;
            justify-content: flex-end;
            padding-right: 16px;
            height: 60px;
            min-width: 250px;
            .fullscreen{
                color: black;
                height: 100%;
                line-height: 60px;
                display: inline-block;
                float: left;
                font-size: 32px;
                cursor:pointer;
            }
        }

    }
</style>
