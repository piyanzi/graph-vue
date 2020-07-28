<template>
  <el-table
    :data="elementInfo"
    border
    style="width: 100%">
      <el-table-column
        label="属性id"
        width="180">
        <template slot-scope="scope">
          <span>{{ scope.row.id}}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="元件id"
        width="180">
        <template slot-scope="scope">
          <span>{{ scope.row.id}}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="属性名称"
        width="180">
      </el-table-column>
      <el-table-column
        label="属性类型"
        width="180">
        <template slot-scope="scope">
          <span>{{ scope.row.name}}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="属性默认值"
        width="200">
        <template slot-scope="scope">
          <span>{{ scope.row.path}}</span>
        </template>
      </el-table-column>
  </el-table>
</template>

<script>
  import axios from 'axios';
  axios.defaults.baseURL = '/api';
  export default {
    name: 'attribute',
    mounted(){
      getAttribute();
      var _this = this;
      function getAttribute(){
        this.$axios.post('/graph/getAttributes')
          .then(function (response) {
            if(response.data.code==0){
              console.log(response.data);
              _this.attributeInfo = response.data.attributes;
            }
            console.log(_this.attributeInfo);
          })
          .catch(function (error) {
            console.log(error);
          });
      }
    },
    data() {
      return {
        attributeInfo:[]
      }
    }
  }
</script>
