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
        >上传</el-button>
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
          label="元件id"
          width="180">
        </el-table-column>
        <el-table-column
          label="元件类型"
          width="180">
        </el-table-column>
        <el-table-column
          prop="name"
          label="元件名称"
          width="180">
        </el-table-column>
        <el-table-column
          prop="path"
          label="元件位置"
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

    <el-dialog title="上传" width="30%" :visible.sync="addFormVisible" @close="closeDialog">

      <!-- 在el-dialog中进行嵌套el-form实现弹出表格的效果 -->
      <el-form :rules="addEditRules" :model="addEditForm" ref="addEditForm">
        <el-form-item label="元件id" label-width="80px" prop="id">
          <el-input v-model="addEditForm.id" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="元件名称" label-width="80px" prop="name">
          <el-input v-model="addEditForm.name" auto-complete="off"></el-input>
        </el-form-item>
        <el-button type="primary" @click="_upload">上传图片</el-button>
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

    <input type="file" id="upload" @change="handle_image($event)" accept=".svg" style="display: none;">

  </div>
</template>

<script>
  var id;
  var name;
  var path;
  export default {
    name: 'elements',
    inject:["reloadElement"],
    methods: {
      closeDialog() {
        this.addEditForm.id = "";
        this.addEditForm.name = "";
        this.addEditForm.path = "";
      },
      _upload() {
        document.getElementById("upload").click();
      },
      handle_image($event) {
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
      handleEdit(row) {
        this.editForm = row;
        id = row.id;
        path = row.path;
        this.editFormVisible = true;
      },
      handleAddEdit(){
        this.addFormVisible = true;
      },
      handleDelete(row) {
        var that = this;
        this.$confirm("永久删除该元件, 是否继续?", "提示", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        })
          .then(() => {
            id = row.id;
            that.$axios.post('/graph/deleteElements',
              {
                "id": id,
              }
            ).then((response) => {
              if (response.data.code == 0) {
                that.tableForm = response.data.elements;
                that.totalCount = response.data.elements.length;
              }
            })
              .catch(function (error) {
                console.log(error);
              });
            this.cancel();
          });
      },
      cancel() {
        this.reloadElement();
        // 取消的时候直接设置对话框不可见即可
        this.editFormVisible = false;
        this.addFormVisible=false;
      },
      addEdit(){
        this.$refs.addEditForm.validate((valid) =>{
          if(valid){
            var that = this;
            id = this.addEditForm.id;
            name = this.addEditForm.name;
            path = this.addEditForm.path;
            console.log(path);
            if(path == "") {
              this.$message("请上传svg文件！");
              return;
            }
            this.$axios.post('/graph/addElements',
              {
                "id": id,
                "name":name,
                "path":path,
              }
            ).then((response)=>{
              if(response.data.code==0){
                that.tableForm = response.data.elements;
                that.totalCount = response.data.elements.length;
              }
            })
              .catch(function (error) {
                console.log(error);
              });
            this.$message("上传成功！");
            this.cancel();
          }
        })
      },
      update() {
        this.$refs.editForm.validate((valid) =>{
          if(valid){
            var data = new FormData();
            var that = this;
            name = this.editForm.name;
            data.append('id',id);
            data.append('name',name);
            data.append('path',path);
            console.log(name);
            this.$axios.post('/graph/setElements',
              {
                "id":id,
                "name":name,
              }
            ).then((response)=>{
              if(response.data.code==0){
                that.tableForm = response.data.elements;
                that.totalCount = response.data.elements.length;
              }
            })
              .catch(function (error) {
                console.log(error);
              });
            this.cancel();
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
      getElement(){
        this.$axios.post('/graph/getElements')
          .then((response)=>{
            if(response.data.code==0){
              this.tableForm = response.data.elements;
              this.totalCount = response.data.elements.length;
            }
          })
          .catch(function (error) {
            console.log(error);
          });
      }
    },
    mounted(){
      this.getElement();
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
          name:'',
          path: '',
        },
        editRules: {
          name: [{required: true, message: '请输入元件名称', trigger: 'change'}],
        },
        addEditRules: {
          path: [{required: true, message: '请输入元件名称', trigger: 'change'}],
          name: [{required: true, message: '请输入元件名称', trigger: 'change'}],
          id: [{required: true, message: '请输入元件id', trigger: 'change'}],
        },
      }
    }
  }
</script>
