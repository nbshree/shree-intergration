<template>
    <div>
        <a-breadcrumb style="margin: 16px 0">
            <a-breadcrumb-item>项目管理</a-breadcrumb-item>
            <a-breadcrumb-item>项目列表</a-breadcrumb-item>
        </a-breadcrumb>
        <a-row>
            <a-col :span="9" class="tools-btn-group">
                <a-button-group>
                    <a-button @click="onAdd">添加</a-button>
                    <a-button @click="onEdit" :disabled="isAllowEdit">修改</a-button>
                    <a-button @click="onDelete" :disabled="isAllowDelete">删除</a-button>
                </a-button-group>
                <a-button-group>
                    <a-button @click="onDataImport">导入</a-button>
                    <a-button @click="onDataExport">导出</a-button>
                </a-button-group>
            </a-col>
            <a-col :span="9"></a-col>
            <a-col :span="5" style="text-align: right; ">
                <a-input-search :v-model="searchText" placeholder="查询条件" @search="onSearch"></a-input-search>
            </a-col>
            <a-col :span="1" style="text-align: right; ">
                <a-button @click="onFilter">筛选</a-button>
            </a-col>
        </a-row>
        <a-table size="middle" :pagination="pagination" :row-selection="rowSelection" :columns="columns" :data-source="dataSource" :rowKey="data => data.id" @change="onTableChange">
            <template slot="bizData" slot-scope="text, record">
                {{record.bizDeptName}}: {{record.bizManagerName}}
            </template>
            <template slot="action" slot-scope="text, record">
                [<a href="javascript:;" @click="onView(record.id)">查看</a>|<a href="javascript:;" @click="onPop(record.projectName)">任务</a>]
            </template>
            <!--            <template>-->
            <!--                <a-pagination show-size-changer :default-current="0" :total="500"></a-pagination>-->
            <!--            </template>-->
        </a-table>
        <project-edit ref="modalEdit" @onClosed="onModalClose"></project-edit>
        <project-filter ref="modalFilter" @onClosed="onModalClose"></project-filter>
    </div>
</template>

<script>
    import projectEdit from "@/views/project/projectEdit";
    import projectFilter from "@/views/project/components/projectFilter";
    import httpAjax from "@/utils/httpAjax";

    const columns = [
        {
            title: "项目编号",
            sorter: true,
            dataIndex: "projectNo"
        },
        {
            title: "项目名称",
            sorter: true,
            dataIndex: "projectName"
        },
        {
            title: "业务小分队",
            scopedSlots: {customRender: "bizData"},
        },
        {
            title: "技术小分队",
            customRender: (text, record) => {
                const techText = `${record.techDeptName}: ${record.techManagerName}`;
                return techText;
            }
        },
        {
            title: "操作",
            key: "action",
            scopedSlots: {customRender: "action"},
        }
    ];

    const dataSource = [];
    for (let i = 0; i < 50; i++) {
        dataSource.push({
            id: `00000${i}`,
            projectNo: `XM000${i}`,
            projectName: `测试项目${i}`,
            bizDeptName: `市场部${i}`,
            bizManagerName: `业务员${i}`,
            techDeptName: `开发部${i}`,
            techManagerName: `技术员${i}`
        });
    }

    export default {
        components: {
            projectEdit,
            projectFilter,
        },
        data() {
            return {
                searchText: '',
                editVisible: false,
                dataSource,
                columns,
                selectedDataKeys: [],
                selectedDataObjects: [],
                isAllowEdit: true,
                isAllowDelete: true,
                pagination: {
                    showTotal: total => `共 ${total} 条数据`,
                    showSizeChanger: true,
                    pageSizeOptions:['10','15','20'],
                    orderField: 'projectNo',
                    orderSort: 'DESC',
                },
            };
        },
        mounted() {
            this.onSearch('');
        },
        computed: {
            rowSelection() {
                return {
                    onChange: (selectedRowKeys, selectedRows) => {
                        this.selectedDataKeys = selectedRowKeys;
                        this.selectedDataObjects = selectedRows;
                        this.isAllowEdit = true;
                        this.isAllowDelete = true;
                        if (this.selectedDataKeys.length == 1) {
                            this.isAllowEdit = false;
                        }
                        if (this.selectedDataKeys.length > 1) {
                            this.isAllowDelete = false;
                        }
                    },
                    getCheckboxProps: record => ({
                        props: {
                            disabled: record.name === "Disabled User", // Column configuration not to be checked
                            name: record.name
                        }
                    })
                };
            }
        },
        methods: {
            onTableChange(pagination, filters, sorter) {
                console.log(pagination);
                const pageData = {...this.pagination};
                pageData.current = pagination.current;
                this.pagination = pageData;
                const params = {
                    keyText: this.searchText,
                    limit: pagination.pageSize,
                    page: pagination.current,
                    orderField: sorter.field ? sorter.field : this.pagination.orderField,
                    orderSort: sorter.order ? sorter.order : this.pagination.orderSort,
                    ...filters,
                }
                this.queryByText(params);
            },
            queryByText(params = {}) {
                console.log('params:', params);
                this.loading = true;
                httpAjax.post('api/pm/project/queryPageByText', params).then((result) => {
                    if (result.status == "1") {
                        const pageData = {...this.pagination};
                        pageData.total = result.count;
                        //pageData.current = result.current;
                        this.dataSource = result.data;
                        this.pagination = pageData;
                    }
                    this.loading = false;
                });
            },
            onModalClose() {
                this.queryByText({
                    keyText: this.searchText,
                });
            },
            onPop(data) {
                alert(data);
            },
            onAdd() {
                this.$refs.modalEdit.onOpen('add');
            },
            onEdit() {
                if (this.selectedDataKeys.length > 0) {
                    this.$refs.modalEdit.onOpen('edit', this.selectedDataKeys[0]);
                } else {
                    const hide = this.$message.warn('没有选择任何可以修改的数据!', 0);
                    setTimeout(hide, 2500);
                }
            },
            onDelete() {
                if (this.selectedDataKeys.length > 0) {
                    this.$confirm({
                        title: "系统提示",
                        content: "确定要删除选中的数据项吗?",
                        onOk() {
                            return new Promise((resolve, reject) => {
                                setTimeout(Math.random() > 0.5 ? resolve : reject, 1000);
                            })
                                .then(() => console.log("成功了"))
                                .catch(() => console.log("Oops errors!"));
                        },
                        onCancel() {
                        }
                    });
                } else {
                    const hide = this.$message.warn('没有选择任何可以删除的数据!', 0);
                    setTimeout(hide, 2500);
                }
            },
            onView(id) {
                if (id) {
                    this.$refs.modalEdit.onOpen('view', id);
                } else {
                    const hide = this.$message.warn('没有选择任何可以查看的数据!', 0);
                    setTimeout(hide, 2500);
                }
            },
            onDataImport() {
            },
            onDataExport() {
            },
            onSearch(text) {
                const params = {
                    keyText: text,
                    limit: 10,
                    page: 1,
                    orderField: 'projectNo',
                    orderSort: 'Desc',
                }
                this.queryByText(params);
            },
            onFilter() {
                this.$refs.modalFilter.onOpen();
            }
        }
    };
</script>

<style>
    .tools-btn-group .ant-btn-group {
        margin-right: 8px;
        margin-bottom: 15px;
    }

    th.column-money,
    td.column-money {
        text-align: right !important;
    }
</style>