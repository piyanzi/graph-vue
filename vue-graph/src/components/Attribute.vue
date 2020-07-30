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
        >新增属性</el-button>
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
        label="属性id"
        width="180">
      </el-table-column>
      <el-table-column
        prop="eid"
        label="元件id"
        width="180">
      </el-table-column>
      <el-table-column
        prop="name"
        label="属性名称"
        width="180">
      </el-table-column>
      <el-table-column
        label="属性类型"
        width="180">
      </el-table-column>
      <el-table-column
        prop="value"
        label="默认值"
        width="200">
      </el-table-column>
      <el-table-column label="操作" width="250pt">
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

    <el-dialog title="新建项目" width="30%" :visible.sync="addFormVisible" @close="closeDialog">

      <!-- 在el-dialog中进行嵌套el-form实现弹出表格的效果 -->
      <el-form :rules="addEditRules" :model="addEditForm" ref="addEditForm">
        <el-form-item label="属性id" label-width="80px" prop="id">
          <el-input v-model="addEditForm.id" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="元件id" label-width="80px" prop="eid">
          <el-input v-model="addEditForm.eid" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="属性名称" label-width="80px" prop="name">
          <el-input v-model="addEditForm.name" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="默认值" label-width="80px" prop="value">
          <el-input v-model="addEditForm.value" auto-complete="off"></el-input>
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
        <el-form-item label="元件id" label-width="80px" prop="eid">
          <el-input v-model="editForm.eid" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="属性名称" label-width="80px" prop="name">
          <el-input v-model="editForm.name" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="默认值" label-width="80px" prop="value">
          <el-input v-model="editForm.value" auto-complete="off"></el-input>
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
  var name;
  var value;
  var eid;
  import Bus from "../assets/Bus.js";
  export default {
    name: "attribute",
    inject:["reloadAttribute"],
    methods: {
      //关闭弹框
      closeDialog() {
        this.addEditForm.id = "";
        this.addEditForm.eid = "";
        this.addEditForm.name = "";
        this.addEditForm.value = "";
      },
      cancel() {
        this.reloadAttribute();
        // 取消的时候直接设置对话框不可见即可
        this.editFormVisible = false;
        this.addFormVisible=false;
      },
      //编辑
      handleEdit(row) {
        this.editForm = row;
        id = row.id;
        eid = row.eid;
        name = row.name;
        value = row.value;
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
            that.$axios.post('/graph/deleteAttributes',
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
            eid = this.addEditForm.eid;
            name = this.addEditForm.name;
            value = this.addEditForm.value;
            this.$axios.post('/graph/addAttributes',
              {
                "id": id,
                "eid": eid,
                "name": name,
                "value": value,
              }
            ).then((response)=>{
              if(response.data.code==0){
                that.$message("新增属性成功！");
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
            value = this.editForm.value;
            eid = this.editForm.eid;
            this.$axios.post('/graph/setAttributes',
              {
                "id":id,
                "eid":eid,
                "name":name,
                "value":value,
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
      getAttribute(){
        this.$axios.post('/graph/getAllAttributes')
          .then((response)=>{
            if(response.data.code==0){
              this.tableForm = response.data.attributes;
              this.totalCount = response.data.attributes.length;
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
        this.currentPage=val
      },
    },
    mounted(){
      this.getAttribute();
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
          eid:'',
          value:'',
        },
        addEditForm: {
          id: '',
          eid: '',
          name:'',
          value: '',
        },
        editRules: {
          name: [{required: true, message: '请输入属性名称', trigger: 'change'}],
          eid: [{required: true, message: '请输入元件id', trigger: 'change'}],
          value: [{required: true, message: '请输入默认值', trigger: 'change'}],
        },
        addEditRules: {
          value: [{required: true, message: '请输入默认值', trigger: 'change'}],
          name: [{required: true, message: '请输入属性名称', trigger: 'change'}],
          eid: [{required: true, message: '请输入元件id', trigger: 'change'}],
          id: [{required: true, message: '请输入属性id', trigger: 'change'}],
        },
      }
    }
  }
</script>

<style scoped>

</style>
