<template>
    <div>
        <!-- search bar -->
        <el-card id="search">
            <el-row>
                <el-col :span="20">
                    <el-input placeholder="username" v-model="searchModel.username" clearable></el-input>
                    <el-input placeholder="phone number" v-model="searchModel.phone" clearable></el-input>
                    <el-button @click="getUserList" type="primary" plain icon="el-icon-search">Search</el-button>
                </el-col>
                <el-col :span="4" align="right">
                    <el-button type="primary" icon="el-icon-plus" circle></el-button>
                </el-col>
            </el-row>
        </el-card>
        <!-- result -->
        <el-card>
            <el-table
                :data="userList"
                style="width: 100%">
                <el-table-column
                label="#"
                width="50">
                    <template slot-scope="scope">
                        {{(searchModel.pageNo-1) * searchModel.pageSize + scope.$index + 1}}
                    </template>
                </el-table-column>
                <el-table-column
                prop="id"
                label="User id"
                width="100">
                </el-table-column>
                <el-table-column
                prop="username"
                label="Username"
                width="180">
                </el-table-column>
                <el-table-column
                prop="phone"
                label="Phone"
                width="180">
                </el-table-column>
                <el-table-column
                prop="email"
                label="Email">
                </el-table-column>
                <el-table-column
                label="Action"
                width="80">
                </el-table-column>
            </el-table>
        </el-card>
        <!-- pagination -->
        <div class="block">
            <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page.sync="searchModel.pageNo"
            :page-sizes="[5, 10, 20, 50]"
            :page-size="searchModel.pageSize"
            layout="total, sizes, prev, pager, next, jumper"
            :total="total">
            </el-pagination>
        </div>
    </div>
</template>

<script>
import userApi from "@/api/usermanage"
export default {
    data(){
        return {
            total: 0,
            searchModel: {
                pageNo: 1,
                pageSize: 10
            },
            userList: []
        }
    },
    methods: {
        handleCurrentChange(pageNo){
            this.searchModel.pageNo = pageNo;
            this.getUserList();
        },
        handleSizeChange(pageSize){
            this.searchModel.pageSize = pageSize;
            this.getUserList();
        },
        getUserList(){
            userApi.getUserList(this.searchModel).then(response => {
                this.userList = response.data.rows;
                this.total = response.data.total;
            });
        }
    },
    created() {
        this.getUserList();
    },

}
</script>

<style>
    #search .el-input{
        width: 200px;
        margin-right: 10px;
    }
</style>
