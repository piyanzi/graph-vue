<template>
  <div id="project">
    <br />
    <el-row>
      <el-col :span="1" class="grid">
        <el-button
          type="success"
          @click="handleAddEdit"
          icon="el-icon-circle-plus-outline"
          size="mini"
          round
        >新建项目</el-button>
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
        label="项目id"
        width="180">
      </el-table-column>
      <el-table-column
        prop="uid"
        label="用户id"
        width="180">
      </el-table-column>
      <el-table-column
        prop="name"
        label="项目名称"
        width="180">
      </el-table-column>
      <el-table-column
        prop="path"
        label="项目文件"
        width="200">
      </el-table-column>
      <el-table-column label="操作" width="250pt">
        <template slot-scope="scope">
          <el-button size="small" type="primary" @click="open(scope.row.id)">打开</el-button>
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

    <el-dialog title="新建项目" width="30%" :visible.sync="addFormVisible" @close="closeDialog" append-to-body>

      <!-- 在el-dialog中进行嵌套el-form实现弹出表格的效果 -->
      <el-form :rules="addEditRules" :model="addEditForm" ref="addEditForm">
        <el-form-item label="项目id" label-width="80px" prop="id">
          <el-input v-model="addEditForm.id" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="用户id" label-width="80px" prop="uid">
          <el-input v-model="addEditForm.uid" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="项目名称" label-width="80px" prop="name">
          <el-input v-model="addEditForm.name" auto-complete="off"></el-input>
        </el-form-item>
        <el-button type="primary" @click="_upload">上传文件（若不选择文件，则生成空模型）</el-button>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancel">取 消</el-button>
        <!-- 设置触发更新的方法 -->
        <el-button type="primary" @click="addEdit">确 定</el-button>
      </div>
    </el-dialog>

    <el-dialog title="编辑" width="30%" :visible.sync="editFormVisible" append-to-body>
      <!-- 在el-dialog中进行嵌套el-form实现弹出表格的效果 -->
      <el-form :rules="editRules" :model="editForm" ref="editForm">
        <el-form-item label="元件名称" label-width="80px" prop="name">
          <el-input v-model="editForm.name" auto-complete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancel">取 消</el-button>
        <!-- 设置触发更新的方法 -->
        <el-button type="primary" @click="update">确 定</el-button>
      </div>
    </el-dialog>

    <input type="file" id="upload_xml" @change="handle_file($event)" accept=".xml" style="display: none;">

  </div>
</template>

<script>
  var id;
  var name;
  var path;
  var uid;
  import Bus from "../assets/Bus.js";
  export default {
    name: "project",
    inject:["reloadProject"],
    methods: {
      //关闭弹框
      closeDialog() {
        this.addEditForm.id = "";
        this.addEditForm.uid = "";
        this.addEditForm.name = "";
        this.addEditForm.path = "";
      },
      _upload() {
        document.getElementById("upload_xml").click();
      },
      cancel() {
        this.reloadProject();
        // 取消的时候直接设置对话框不可见即可
        this.editFormVisible = false;
        this.addFormVisible=false;
      },
      //编辑
      handleEdit(row) {
        this.editForm = row;
        id = row.id;
        uid = row.uid;
        path = row.path;
        this.editFormVisible = true;
      },
      //删除
      handleDelete(row) {
        var that = this;
        this.$confirm("永久删除该项目, 是否继续?", "提示", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        })
          .then(() => {
            id = row.id;
            that.$axios.post('/graph/deleteProjects',
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
      addEdit(){
        this.$refs.addEditForm.validate((valid) =>{
          if(valid){
            var that = this;
            id = this.addEditForm.id;
            uid = this.addEditForm.uid;
            name = this.addEditForm.name;
            path = this.addEditForm.path;
            console.log(path);
            this.$axios.post('/graph/addProjects',
              {
                "id": id,
                "uid": uid,
                "name": name,
                "path": path,
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
            name = this.editForm.name;
            console.log(name);
            console.log(id);
            this.$axios.post('/graph/setProjects',
              {
                "id":id,
                "name":name,
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
      //新建任务
      handleAddEdit() {
          this.addFormVisible = true;
      },
      //处理上传的文件
      handle_file($event) {
        var _this = this;
        var files = event.target.files;
        if (files != null) {
          var file = files[0];
          var reader = new FileReader();
          reader.onload = function (e) {
            _this.addEditForm.path = e.target.result;
            _this.$message("文件已添加");
          }
          reader.readAsText(file);
        }
      },
      //打开项目
      open(id) {
        Bus.$emit("curProjectId", id);
        Bus.$emit("changeTab", "graph");
      },
      //拉取项目
      getProject(){
        this.$axios.post('/graph/getProjects')
          .then((response)=>{
            if(response.data.code==0){
              this.tableForm = response.data.projects;
              this.totalCount = response.data.projects.length;
              Bus.$emit("nextId",this.totalCount);
            }
          })
          .catch(function (error) {
            console.log(error);
          });
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
        this.currentPage=val;
      },
    },
    mounted(){
      this.getProject();

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
          name:'',
        },
        addEditForm: {
          id: '',
          uid: '',
          name:'',
          path: '',
        },
        editRules: {
          name: [{required: true, message: '请输入项目名称', trigger: 'change'}],
        },
        addEditRules: {
          name: [{required: true, message: '请输入项目名称', trigger: 'change'}],
          uid: [{required: true, message: '请输入用户id', trigger: 'change'}],
          id: [{required: true, message: '请输入项目id', trigger: 'change'}],
        },
      }
    }
  }
</script>

<style scoped>

</style>
