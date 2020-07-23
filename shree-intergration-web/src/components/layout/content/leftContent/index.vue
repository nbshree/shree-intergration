<template>
    <a-layout>
        <a-layout-sider width=240 class="layout-sider" v-model="collapsed.sliderState">
            <a-menu theme="dark"
                    :default-selected-keys="[current]"
                    :default-open-keys="[defaultOpenKeys]"
                    mode="inline"
                    @click="handleClick">
                <template v-for="(item) in routes">
                    <a-menu-item :key="'/'+item.children[0].path" v-if="item.single">{{item.name}}</a-menu-item>
                    <a-sub-menu :key="item.path" v-else>
                        <span slot="title">
                            <a-icon :type="item.icon"/>
                            <span>{{item.name}}</span>
                        </span>
                        <template v-for="(items) in item.children">
                            <a-menu-item :key="item.path+'/'+items.path">{{items.name}}</a-menu-item>
                        </template>
                    </a-sub-menu>
                </template>
            </a-menu>
        </a-layout-sider>
        <a-layout-content style="margin: 0 16px">
            <router-view/>
        </a-layout-content>
        <button @click="cilck1"></button>
    </a-layout>
</template>

<script>

    import {mapActions, mapGetters} from "vuex";

    export default {
        name: 'leftContent',
        components: {},
        created() {
            this.current = this.$route.path;
            this.defaultOpenKeys = this.$route.matched[0].path;
        },
        mounted() {
            // console.log(this.$route.filter(item=>!item.hidden))
            console.log(this.$route.matched[0])
            console.log(this.$route.matched[0].path)
            console.log(this.$route.path)


        },
        watch: {
            '$route'(e) {
                this.current = e.path;
            }
        },
        data() {
            return {
                current: "403",
                defaultOpenKeys: ""
            };
        },
        computed: {
            ...mapGetters({'collapsed': 'sidebar'}),
            routes() {
                return this.$router.options.routes.filter(item => !item.hidden)
            }

        },
        methods: {
            ...mapActions('app', ['setSliderState']),
            handleClick(e) {
                console.log(e)
                console.log(e.key)
                this.current = e.key;
                this.$router.push({path: e.key})
            },
            cilck1(){
                this.defaultOpenKeys = this.$route.matched[0].path;
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

            .category {
                height: 100%;
                font-size: 32px;
                color: #41b883;
                display: inline-block;
                line-height: 60px;
                padding: 0 20px;
                cursor: pointer;
                float: left;
            }

            .middle-area__msglist {
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

            .fullscreen {
                color: black;
                height: 100%;
                line-height: 60px;
                display: inline-block;
                float: left;
                font-size: 32px;
                cursor: pointer;
            }
        }

    }
</style>
