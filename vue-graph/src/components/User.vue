<template>
  <div>
    <br />
    <el-row>
      <el-col :span="1" class="grid">
        <el-button
          type="success"
          @click="handleAddEdit"
          icon="el-icon-circle-plus-outline"
          size="mini"
          round
        >新建用户</el-button>
      </el-col>
    </el-row>
    <br />
    <el-table
      :data="tableForm.slice((currentPage-1)*PageSize,currentPage*PageSize)"
      border
      ref="tableForm"
      style="width: 100%"
    >
      <el-table-column
        prop="id"
        label="用户id"
        width="180">
      </el-table-column>
      <el-table-column
        prop="username"
        label="用户名"
        width="180">
      </el-table-column>
      <el-table-column
        prop="password"
        label="密码"
        width="180">
      </el-table-column>
      <el-table-column
        prop="role"
        label="用户类型"
        width="200">
      </el-table-column>
      <el-table-column label="操作" width="200pt">
        <template slot-scope="scope">
          <el-button size="small" @click="handleEdit(scope.row)">编辑</el-button>
          <el-button size="small" type="danger" @click="handleDelete(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <div class="tabListPage">
      <el-pagination @size-change="handleSizeChange"
                     @current-change="handleCurrentChange"
                     :current-page="currentPage"
                     :page-sizes="pageSizes"
                     :page-size="PageSize" layout="total, sizes, prev, pager, next, jumper"
                     :total="totalCount">
      </el-pagination>
    </div>

    <el-dialog title="新建用户" width="30%" :visible.sync="addFormVisible" @close="closeDialog">

      <!-- 在el-dialog中进行嵌套el-form实现弹出表格的效果 -->
      <el-form :rules="addEditRules" :model="addEditForm" ref="addEditForm">
        <el-form-item label="用户id" label-width="80px" prop="id">
          <el-input v-model="addEditForm.id" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="用户名" label-width="80px" prop="username">
          <el-input v-model="addEditForm.username" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="密码" label-width="80px" prop="password">
          <el-input v-model="addEditForm.password" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="用户类型" label-width="80px" prop="role">
          <el-select v-model="addEditForm.role" placeholder="请选择用户类型">
            <el-option label="管理员" value="管理员"></el-option>
            <el-option label="普通用户" value="普通用户"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancel">取 消</el-button>
        <!-- 设置触发更新的方法 -->
        <el-button type="primary" @click="addEdit">确 定</el-button>
      </div>
    </el-dialog>

    <el-dialog title="编辑" width="30%" :visible.sync="editFormVisible" >
      <!-- 在el-dialog中进行嵌套el-form实现弹出表格的效果 -->
      <el-form :rules="editRules" :model="editForm" ref="editForm">
        <el-form-item label="用户名" label-width="80px" prop="username">
          <el-input v-model="editForm.username" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="密码" label-width="80px" prop="password">
          <el-input v-model="editForm.password" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="用户类型" label-width="80px" prop="role">
          <el-select v-model="editForm.role" placeholder="请选择用户类型">
            <el-option label="管理员" value="管理员"></el-option>
            <el-option label="普通用户" value="普通用户"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancel">取 消</el-button>
        <!-- 设置触发更新的方法 -->
        <el-button type="primary" @click="update">确 定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
  var id;
  var username;
  var password;
  var role;
  export default {
    name: "user",
    inject:["reloadUser"],
    methods: {
      closeDialog() {
        this.addEditForm.id = "";
        this.addEditForm.username = "";
        this.addEditForm.password = "";
        this.addEditForm.role = "";
      },
      handleEdit(row) {
        this.editForm = row;
        id = row.id;
        this.editFormVisible = true;
      },
      handleAddEdit(){
        this.addFormVisible = true;
      },
      handleDelete(row) {
        var that = this;
        this.$confirm("永久删除该用户, 是否继续?", "提示", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        })
          .then(() => {
            id = row.id;
            that.$axios.post('/graph/deleteUsers',
              {
                "id": id,
              }
            ).then((response) => {
              if (response.data.code == 0) {
                that.cancel();
              }
            })
              .catch(function (error) {
                console.log(error);
              });

          });
      },
      cancel() {
        this.reloadUser();
        // 取消的时候直接设置对话框不可见即可
        this.editFormVisible = false;
        this.addFormVisible=false;
      },
      addEdit(){
        this.$refs.addEditForm.validate((valid) =>{
          if(valid){
            var that = this;
            id = this.addEditForm.id;
            username = this.addEditForm.username;
            password = this.addEditForm.password;
            role = this.addEditForm.role == "管理员" ? 1 : 0;
            this.$axios.post('/graph/addUsers',
              {
                "id": id,
                "username": username,
                "password": password,
                "role": role,
              }
            ).then((response)=>{
              if(response.data.code==0){
                that.$message("上传成功！");
                that.cancel();
              }
            })
              .catch(function (error) {
                console.log(error);
              });

          }
        })
      },
      update() {
        this.$refs.editForm.validate((valid) =>{
          if(valid){
            var that = this;
            username = this.editForm.username;
            password = this.editForm.password;
            role = this.editForm.role == "管理员" ? 1 : 0;
            this.$axios.post('/graph/setUsers',
              {
                "id": id,
                "username": username,
                "password": password,
                "role": role,
              }
            ).then((response)=>{
              if(response.data.code==0){
                that.cancel();
              }
            })
              .catch(function (error) {
                console.log(error);
              });
          }
        })
      },
      // 分页
      // 每页显示的条数
      handleSizeChange(val) {
        // 改变每页显示的条数
        this.PageSize=val
        // 注意：在改变每页显示的条数时，要将页码显示到第一页
        this.currentPage=1
      },
      // 显示第几页
      handleCurrentChange(val) {
        // 改变默认的页数
        this.currentPage=val
      },
      getUsers(){
        this.$axios.post('/graph/getUsers')
          .then((response)=>{
            if(response.data.code==0){
              for(var index in response.data.users) {
                if(response.data.users[index].role == "1") {
                  response.data.users[index].role = "管理员";
                }
                else  {
                  response.data.users[index].role = "普通用户";
                }

              }
              this.tableForm = response.data.users;
              this.totalCount = response.data.users.length;
            }
          })
          .catch(function (error) {
            console.log(error);
          });
      }
    },
    mounted(){
      this.getUsers();
    },
    data() {
      return {
        tableForm:[],
        // 默认显示第几页
        currentPage:1,
        // 总条数，根据接口获取数据长度(注意：这里不能为空)
        totalCount:1,
        // 个数选择器（可修改）
        pageSizes:[10,20,30,50],
        // 默认每页显示的条数（可修改）
        PageSize:10,
        editFormVisible: false,
        addFormVisible: false,
        editForm: {
          username:'',
          password:'',
          role: '',
        },
        addEditForm: {
          id: '',
          username:'',
          password:'',
          role:'',
        },
        editRules: {
          username: [{required: true, message: '请输入用户名', trigger: 'change'}],
          password: [{required: true, message: '请输入密码', trigger: 'change'}],
          role: [{required: true, message: '请选择用户类型', trigger: 'change'}],
        },
        addEditRules: {
          username: [{required: true, message: '请输入用户名', trigger: 'change'}],
          password: [{required: true, message: '请输入密码', trigger: 'change'}],
          id: [{required: true, message: '请输入用户id', trigger: 'change'}],
          role: [{required: true, message: '请选择用户类型', trigger: 'change'}],
        },
      }
    }
  }
</script>

<style scoped>

</style>
