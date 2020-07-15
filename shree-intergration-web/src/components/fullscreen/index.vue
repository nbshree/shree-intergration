<template>
    <div @click="handleScreenFull">
        <a-icon :component="componentSvg"/>
    </div>
</template>

<script>
    import screenfull from 'screenfull';
    import {fullScreenSvg, fullScreenZoomOutSvg} from '@/assets/svg/svg';

    export default {
        name: 'fullscreen',
        props: {
            enabled: {
                type: Boolean,
                default: true
            }
        },
        computed: {
            componentSvg() {
                return this.fulled ? fullScreenZoomOutSvg : fullScreenSvg;
            }
        },
        data() {
            return {
                fullScreenSvg,
                fulled: false,
            };
        },
        methods: {
            handleScreenFull() {
                this.fulled = !this.fulled;
                if (!this.enabled) return null
                if (!screenfull.enabled) {
                    this.$message.error('fullscreen can not work');
                    return false;
                }
                screenfull.toggle();
            }
        }
    };
</script>

<!--<style lang="stylus" scoped>-->
<!--.svg-icon-->
<!--  vertical-align middle-->
<!--  cursor pointer-->
<!--  color #515151-->
<!--  transition color .28s-->
<!--  &:hover-->
<!--    color #41b883-->
<!--</style>-->
