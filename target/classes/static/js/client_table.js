new Vue({
    el:'#app',
    data: {
        formSearch: {
            clientId: '',
            clientName: '',
            clientPhone: ''
        },
        visible:false,
        formAdd: {
            clientId: '',
            clientName: '',
            clientPhone: ''
        },
        edit_visible: false,
        formEdit_after: {
            id_after: '',
            name_after: '',
            phone_after: ''
        },
        formEdit_before: {
            id_before: '',
            name_before: '',
            phone_before: ''
        },
        tableData: null,
        total: 100, //设定total，既总条数后，el-pagination会算出有多少页
        current_page: 1,
        page_size: 6,

    },
    mounted:function(){
        this.handleDisplay();
    },

    methods: {

        current_change:function(clicked_page){ //在点选了分页条中的某一页后，由current_change所触发的函数的参数即是点选的页数
            this.current_page = clicked_page;
            this.handleDisplay();
            // alert(this.current_page);
        },

        prev_click:function(){
            this.current_page = this.current_page - 1;
        },

        next_click:function(){
            this.current_page = this.current_page + 1;
        },

        handleDisplay(){
            axios
                .get('/DisplayClientInfo',{
                    params: {
                        info_per_page: this.page_size,
                        start: this.current_page,
                    }
                })
                .then(response => (
                        console.log(response),
                        this.total = response.data[0],
                        this.tableData = response.data[1] //完全可按照数组来处理
                    )
                )
                .catch(function (error) { // 请求失败处理
                    console.log(error);
                });
        },

        handleSearch() {
            /* json格式提交： */
            // let formData = JSON.stringify(this.formMess);

            /* formData格式提交： */
            if (this.formSearch.clientId === '' && this.formSearch.clientName === '' && this.formSearch.clientPhone === ''){
                this.$message.error('啥都不输入你查啥？');
            }
            else {
                axios.get('/SearchClientInfo',{
                    params: {
                        id_search: this.formSearch.clientId,
                        name_search: this.formSearch.clientName,
                        phone_search: this.formSearch.clientPhone
                    }
                })
                    .then(response => (
                        console.log(response),
                            this.total = response.data[0],
                            this.tableData = response.data[1] //完全可按照数组来处理
                        )
                    )
                    .catch(function (error) {
                        console.log(error);
                    });
            }

        },

        handleAdd(){
            let _this = this;
            if (this.formAdd.clientId === '' && this.formAdd.clientName === '' && this.formAdd.clientPhone === ''){
                this.$message.error('啥都不输入你想添加啥？');
            }
            else {
                axios.get('/AddClientInfo',{
                    params: {
                        clientId: this.formAdd.clientId,
                        clientName: this.formAdd.clientName,
                        clientPhone: this.formAdd.clientPhone
                    }
                })
                    .then(function () {
                        _this.handleDisplay()
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
                this.formAdd.clientId = "";
                this.formAdd.clientName = "";
                this.formAdd.clientPhone = "";
                this.visible = false;
            }


        },

        showEdit(row){
            this.edit_visible = true;
            this.formEdit_before.id_before = row.id;
            this.formEdit_before.name_before = row.name;
            this.formEdit_before.phone_before = row.phone;
        },

        confirmEdit(){
            const  _this = this;
            axios.get('/EditClientInfo',{
                params: {
                    id_after: this.formEdit_after.id_after,
                    name_after: this.formEdit_after.name_after,
                    phone_after: this.formEdit_after.phone_after,
                    id_before: this.formEdit_before.id_before,
                    name_before: this.formEdit_before.name_before,
                    phone_before: this.formEdit_before.phone_before
                }
            })
                .then(function () {
                    _this.handleDisplay()
                })
                .catch(function (error) {
                    console.log(error);
                });
            this.edit_visible = false;
        },

        handleDelete(row){
            const _this = this;
            axios.get('/DeleteClientInfo',{
                params: {
                    id: row.id,
                    name: row.name,
                    phone: row.phone
                }
            }).then(function () {
                _this.handleDisplay()
            })
                .catch(function (error) {
                    console.log(error);
                });
        },

        /**
         * 以下是左侧目录的点击跳转事件
         */
        handle_client_manage(){
            window.location.href="/client_table";
        },

        handle_tomain(){
            window.location.href="/main";
        },

        handleOpen(){

        },

        handleClose(){

        }

    }
});