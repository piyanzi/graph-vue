<template>
    <div id="project">
      <el-button type="primary" size="small" @click="open(id_1)">{{id_1}}</el-button>
      <el-button type="primary" size="small" @click="open(id_2)">{{id_2}}</el-button>
    </div>
</template>

<script>
  import Bus from "../assets/Bus.js";
  export default {
    name: "Project",
    methods: {
      open(id) {
        Bus.$emit("curProjectId", id);
        Bus.$emit("changeTab", "graph");
      }
    },
    data() {
      return {
        id_1: 1,
        id_2: 2,
      }
    },
    mounted() {
    },
  }
</script>

<style scoped>

</style>
