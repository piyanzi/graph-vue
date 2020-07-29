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
<!--      <el-upload-->
<!--        class="upload-demo"-->
<!--        ref="upload"-->
<!--        accept="image/gif,image/jpeg,image/jpg,image/png,image/svg"-->
<!--        action="https://jsonplaceholder.typicode.com/posts/"-->
<!--        :on-preview="handlePreview"-->
<!--        :on-remove="handleRemove"-->
<!--        multiple-->
<!--        :limit="1"-->
<!--        :file-list="fileList"-->
<!--        :auto-upload="false">-->
<!--        <el-button size="small" type="primary">点击上传</el-button>-->
<!--        <div slot="tip" class="el-upload__tip">请上传元件图片</div>-->
<!--      </el-upload>-->

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
  import axios from 'axios';
  axios.defaults.baseURL = '/api';
  var id;
  var name;
  var path;
  export default {
    name: 'user',
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
      handleRemove(file, fileList) {
        console.log(file, fileList);
      },
      handlePreview(file) {
        console.log(file);
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
        // 设置类似于console类型的功能
        this.$confirm("永久删除该元件, 是否继续?", "提示", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        })
          .then(() => {
            // 移除对应索引位置的数据，可以对row进行设置向后台请求删除数据
            this.tableForm.splice(1);
            this.$message({
              type: "success",
              message: "删除成功!"
            });
          })
          .catch(() => {
            this.$message({
              type: "info",
              message: "已取消删除"
            });
          });
      },
      cancel() {
        location.reload();
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
        fileList:[],
        tableForm:[],
        addForm: [],
        // 默认显示第几页
        currentPage:1,
        // 总条数，根据接口获取数据长度(注意：这里不能为空)
        totalCount:1,
        // 个数选择器（可修改）
        pageSizes:[5,10,15,20],
        // 默认每页显示的条数（可修改）
        PageSize:5,
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
          name: [{required: true, message: '请输入元件名称', trigger: 'change'}],
        },
      }
    }
  }
</script>
