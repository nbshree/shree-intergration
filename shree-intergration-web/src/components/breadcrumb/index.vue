<template>
    <div>
        <a-breadcrumb :routes="list">
            <template slot="itemRender" slot-scope="{ route, params, routes, paths }">
                <span v-if="routes.indexOf(route) === routes.length - 1">
                  {{ route.name }}
                </span>
                <router-link v-else :to="`/${paths.join('/')}`">
                    {{ route.name }}
                </router-link>
            </template>
        </a-breadcrumb>
        <br/>
        {{ $route.path }}
    </div>
</template>


<script>
    export default {
        name: 'breadcrumb',
        data() {
            return {
                basePath: '',
                list: null
            };
        },
        created() {
            this.getBreadcrumb();
            console.log(this.$route.path)
            console.log(this.list)
            console.log(this.$route)
            console.log(this.$route.matched)
            console.log(this.$router.options.routes)
        },
        watch: {
            $route() {
                this.getBreadcrumb();
            }
        },
        methods: {
            getBreadcrumb() {
                console.log("1111111111")
                console.log(this.$route.path)
                this.basePath = this.$route.path;
                // let matched = this.$route.matched.filter((item) => item.name);
                // if (matched[0] && matched[0].name !== 'dashboard') {
                //     matched = [{path: '/dashboard', meta: {title: 'dashboard'}}].concat(matched);
                // }
                // this.list = matched.filter((item) => item.meta && item.meta.title && item.meta.breadcrumb !== false);
                this.list = this.$route.matched;
            },
            getTitle(title) {
                if (this.$te(`route.${title}`)) {
                    return this.$t(`route.${title}`);
                }
                return title;
            }
        }
    };
</script>

<style lang="stylus">
    .breadcrumb-enter-active,
    .breadcrumb-leave-active
        transition: all .5s

    .breadcrumb-enter,
    .breadcrumb-leave-active
        opacity: 0
        transform: translateX(20px)

    .breadcrumb-move
        transition: all .5s

    .breadcrumb-leave-active
        position: absolute
</style>

<style lang="stylus" scoped>
    .eden-breadcrumb
        display: inline-block
        min-width: 50px
        line-height: 60px

        .el-breadcrumb
            display: inline-block
            font-size: 14px
            line-height: 60px
            margin-left: 10px

            .no-redirect
                cursor: text
</style>
