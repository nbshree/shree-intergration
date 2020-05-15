<template>
    <div>
        {{title}}
        {{title}}
        <a-rate v-model="test1Value"/>
        <router-view name="test1"/>
        <router-view/>

        <Test2 message1="静态值" :message2="title"></Test2>
    </div>
</template>
<script>
    import Test2 from './test2';

    export default {
        components: {
            Test2
        },
        data() {
            return {
                title: "test1",
                test1Value: 1
            };
        },
        computed: {},
        methods: {},
    };
</script>