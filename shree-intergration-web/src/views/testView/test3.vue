<template>
    <div>
        {{title}}
        <a-rate v-model="test1Value"/>
    </div>
</template>
<script>

    export default {
        components: {},
        data() {
            return {
                title: "test3",
                test1Value: 3
            };
        },
        computed: {},
        methods: {},
    };
</script>