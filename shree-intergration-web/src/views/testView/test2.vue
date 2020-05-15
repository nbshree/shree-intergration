<template>
    <div>
        {{title}}
        {{message1}}
        <!--        {{myProp}}-->
        <a-rate v-model="test1Value"/>
    </div>
</template>
<script>

    export default {
        components: {},
        created() {
            //直接使用this获取到props内的值
            console.log(this.message1) // 静态值
            console.log(this.message2) // 动态值
        },
        // props: ['message1', 'message2'],
        props: {
            message1: {
                type: String,
                default: "message1"
            },
            message2: {
                type: String
            }
        },
        data() {
            return {
                title: "test2",
                test1Value: 2,
            };
        },
        computed: {
            // myProp:this.myProps
        },

        methods: {},
    };
</script>