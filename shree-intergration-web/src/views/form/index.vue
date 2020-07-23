<template>
    <div>
        <button @click="onClick">11</button>
        <Form ref="myForm"></Form>
    </div>
</template>
<script>
    import Form from "@/components/form/Form";

    export default {
        components: {
            Form
        },
        computed: {},
        data() {
            return {};
        },
        methods: {
            onClick() {
                this.$refs.myForm.consoleMe("6666666666666");
            }
        },
    };
</script>

<style lang="less">
    section {
        height: 100%;
    }

    #components-layout-demo-side {
        .logo {
            height: 32px;
            --background: rgba(255, 255, 255, 0.2);
            margin: 16px;
            font-size: 24px;
            font-weight: bold;
            color: aliceblue;
            text-align: center;
        }

        .title {
            font-size: 24px;
            font-weight: bold;
            color: black;
        }

        .header {
            padding: 0;
            height: auto;
        }

        /*.layout-sider{*/
        /*    width: 240px;*/
        /*}*/
    }

</style>
