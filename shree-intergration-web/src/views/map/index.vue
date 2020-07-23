<template>
<!--        <button @click="onClick">11</button>-->
<!--        <Form ref="myForm"></Form>-->
    <div style="height: 100%;width: 100%;margin: 0">
        <el-amap v-show="loaded" ref="map" vid="amapDemo" :amap-manager="amapManager" :center="center" :zoom="zoom" :plugin="plugin" :events="events" class="amap-demo">
        </el-amap>
        <div v-show="!loaded">加载地图中。。。。</div>
    </div>

</template>
<script>
    import VueAMap from 'vue-amap';
    let amapManager = new VueAMap.AMapManager();
    export default {
        components: {
            // AMap
        },
        computed: {},
        mounted() {
            setTimeout(()=>{this.loaded=true}
            ,5000)
        },
        data() {
            return {
                loaded:false,
                amapManager,
                zoom: 12,
                center: [121.59996, 31.197646],
                events: {
                    init: (o) => {
                        console.log(o.getCenter())
                        console.log(this.$refs.map.$$getInstance())
                        o.getCity(result => {
                            console.log(result)
                        })
                    },
                    'moveend': () => {
                    },
                    'zoomchange': () => {
                    },
                    'click': (e) => {
                        alert('map clicked');
                    }
                },
                plugin: ['ToolBar', {
                    pName: 'MapType',
                    defaultType: 0,
                    events: {
                        init(o) {
                            console.log(o);
                        }
                    }
                }]
            };

        },
        methods: {
        },
    };
</script>

<style lang="less">
    .amap-demo {
        height: 300px;
    }

</style>
