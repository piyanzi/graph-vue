<template>
  <div id="graph">
    <div class="main-div">
      <div id="headContainer" class="head-container">
        <el-row style="margin-left: 120px">
          <el-button-group>
            <el-button type="primary" size="small" @click="readModel" icon="el-icon-refresh">读取模型</el-button>
            <el-button type="primary" size="small" @click="saveModel" icon="el-icon-upload">保存模型</el-button>
          </el-button-group>
          <el-button-group>
            <el-button type="primary" size="small" @click="zoomOut" icon="el-icon-zoom-out">缩小</el-button>
            <el-button type="primary" size="small" @click="zoomIn" icon="el-icon-zoom-in">放大</el-button>
          </el-button-group>
          <el-button-group>
            <el-button type="primary" size="small" @click="selectAll" icon="el-icon-thumb">全选</el-button>
          </el-button-group>
          <el-button-group>
            <el-button type="danger" size="small" @click="remove" icon="el-icon-delete-solid">删除</el-button>
          </el-button-group>
          <el-button-group>
            <el-button type="primary" size="small" @click="undo" icon="el-icon-back">撤销</el-button>
            <el-button type="primary" size="small" @click="redo" icon="el-icon-right">重做</el-button>
          </el-button-group>
          <el-button-group>
            <el-button type="primary" size="small" @click="scaleRaw">原始大小</el-button>
            <el-button type="primary" size="small" @click="scaleBest">最佳大小</el-button>
          </el-button-group>
        </el-row>
        <el-row style="margin-left: 120px">
          <el-button-group>
            <el-button type="success" size="small" @click="_import" icon="el-icon-download">导入XML</el-button>
            <el-button type="success" size="small" @click="exportXML" icon="el-icon-upload2">导出XML</el-button>
            <el-button type="success" size="small" @click="viewXML" icon="el-icon-monitor">展示XML</el-button>
          </el-button-group>
          <el-button-group>
            <el-button type="success" size="small" @click="_importJSON" icon="el-icon-download">导入JSON</el-button>
            <el-button type="success" size="small" @click="exportJSON" icon="el-icon-upload2">导出JSON</el-button>
            <el-button type="success" size="small" @click="viewJSON" icon="el-icon-monitor">JSON展示</el-button>
          </el-button-group>
          <el-button-group>
            <el-button type="success" size="small" @click="exportPDF" icon="el-icon-picture-outline">导出PDF</el-button>
          </el-button-group>
        </el-row>
      </div>
      <div id="tbContainer" class="left-container"></div>
      <div id="container" class="main-container"></div>
      <div id="rightContainer" class="right-container"></div>
    </div>

    <input
      type="file"
      id="importXML"
      @change="handle_file($event)"
      accept=".xml"
      style="display: none;"
    />

    <input
      type="file"
      id="importJSON"
      @change="handle_file2($event)"
      accept=".json"
      style="display: none;"
    />

    <el-dialog
      title="新增元件赋值"
      :visible.sync="dialogFormVisible"
      width="40%"
      @close="closeDialog"
      center
    >
      <el-form ref="form" :model="form" :rules="rules" :inline="true">
        <el-form-item label="元件名称" label-width="120px" prop="itemName">
          <el-input v-model="form.itemName" placeholder="请输入元件名称"></el-input>
        </el-form-item>
        <div v-for="(item, index) in form.attrs" :key="index">
          <el-form-item
            label="属性名"
            label-width="120px"
            :prop="'attrs.' + index + '.attrName'"
            :rules="[
              {pattern: /^[\u4E00-\u9FA5A-Za-z].*$/, message: '不能以数字或特殊字符开头', trigger: 'change'},
              {required: true, message: '属性名不能为空', trigger: 'change'}, ]"
          >
            <el-input v-model="item.attrName" placeholder="请输入属性名"></el-input>
          </el-form-item>
          <el-form-item label="属性值" label-width="120px" :prop="'attrs.' + index + '.attrValue'">
            <el-input v-model="item.attrValue" placeholder="请输入属性值"></el-input>
          </el-form-item>
          <el-form-item>
            <i class="el-icon-delete" @click="delCurAttr(index)"></i>
          </el-form-item>
        </div>
        <el-form-item label="单位" label-width="120px" prop="itemName">
          <el-input v-model="form.attrUnit" placeholder="请输入单位"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button id="addAttr" @click="addAttr">新增属性</el-button>
        <el-button id="delAttr" @click="delAttr">删除属性</el-button>
        <el-button type="primary" @click="addNewElement('form')">提交</el-button>
      </div>
    </el-dialog>

    <el-dialog title="编辑" width="30%" :visible.sync="editFormVisible">
      <el-form :rules="editRules" :model="editForm" ref="editForm">
        <el-form-item label="元件名称" label-width="80px" prop="eid">
          <el-input v-model="editForm.eid" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="属性名称" label-width="80px" prop="name">
          <el-input v-model="editForm.name" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="属性名" label-width="80px" prop="value">
          <el-input v-model="editForm.value" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="单位" label-width="80px" prop="unit">
          <el-input v-model="editForm.unit" auto-complete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancel">取 消</el-button>
        <el-button type="primary" @click="update">确 定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
import Bus from "../assets/Bus.js";
// const {mxGraph, mxClient, mxCodec, mxUtils, mxConnectionHandler, mxEvent, mxGraphHandler,
//   mxKeyHandler, mxImage, mxGraphModel, mxRubberband, mxUndoManager, mxForm, mxRectangle, mxPrintPreview} = mxgraph;
// import {
//   mxToolbar,
//   mxGraphModel,
//   mxGraph,
//   mxCell,
//   mxGeometry,
//   mxEvent,
//   mxUtils,
//   mxConnectionHandler,
//   mxImage,
//   mxKeyHandler,
//   mxGraphHandler,
//   mxRubberband,
//   mxUndoManager,
//   mxClient
// } from 'mxgraph/javascript/mxClient'
var graph;
var model;
var tbContainer;
var headContainer;
var container;
var rightContainer;
var undoManager;
var xml;
var doc;
//存储工具栏信息
var elementInfo = [];
var curElement = {};
var elementAttrList = [];
//新增元件相关全局变量
var curX;
var curY;
var curFile;

var eid;
var name;
var value;
var unit;
//旋转
mxVertexHandler.prototype.rotationEnabled = true;
export default {
  name: "graph",
  inject: ["reloadAttribute"],
  data() {
    return {
      form: {
        itemName: "",
        attrs: [],
      },
      projectId: "",
      rules: {
        itemName: [
          {
            pattern: /^[\u4E00-\u9FA5A-Za-z].*$/,
            message: "不能以数字或特殊字符开头",
            trigger: "change",
          },
          { required: true, message: "元件名称不能为空", trigger: "change" },
        ],
      },
      dialogFormVisible: false,
      editFormVisible: false,
      editForm: {
        name: "",
        eid: "",
        value: "",
        unit: "",
      },
      editRules: {
        name: [
          { required: true, message: "请输入属性名称", trigger: "change" },
        ],
        eid: [{ required: true, message: "请输入元件id", trigger: "change" }],
        value: [{ required: true, message: "请输入默认值", trigger: "change" }],
        unit: [{ required: true, message: "请输入单位", trigger: "change" }],
      }
    };
  },
  methods: {
    //取消
    cancel() {
      //this.reloadAttribute();
      // 取消的时候直接设置对话框不可见即可
      this.editFormVisible = false;
    },
    //更新
    update() {
      this.$refs.editForm.validate((valid) => {
        var cell2 = graph.getSelectionCell();
        var doc = mxUtils.createXmlDocument();
        var node = doc.createElement(this.editForm.eid);
        node.setAttribute(this.editForm.name, this.editForm.value);
        node.setAttribute("单位", this.editForm.unit);
        cell2.setValue(node);
        graph.refresh();
        this.editFormVisible = false;
      });
    },
    //放大
    zoomIn() {
      graph.zoomIn();
      this.$message("放大到" + (graph.view.scale * 100).toString() + "%");
    },
    //缩小
    zoomOut() {
      graph.zoomOut();
      this.$message("缩小到" + (graph.view.scale * 100).toString() + "%");
    },
    //全选
    selectAll() {
      graph.selectAll();
    },
    //删除
    remove() {
      if (graph.isEnabled()) {
        graph.removeCells(graph.getSelectionCells());
      }
    },

    //旋转
    // rotate() {
    //   if(graph.isEnabled()) {
    //     graph.getSelectionCells().rotate90();
    //   }
    // },
    //撤销
    undo() {
      undoManager.undo();
    },
    //重做
    redo() {
      undoManager.redo();
    },
    //最佳大小
    scaleBest() {
      graph.selectAll();
      if (graph.getSelectionCells().length == 0) {
        this.$message("当前画布没有内容！");
        return;
      }
      graph.clearSelection();
      var bounds = graph.getGraphBounds();
      var t = graph.view.translate;
      var s = graph.view.scale;
      bounds.width /= s;
      bounds.height /= s;
      bounds.x = bounds.x / s - t.x;
      bounds.y = bounds.y / s - t.y;
      var cw = graph.container.clientWidth - 10;
      var ch = graph.container.clientHeight - 10;
      var scale =
        Math.floor(20 * Math.min(cw / bounds.width, ch / bounds.height)) / 20;
      graph.zoomTo(scale);
      this.$message(
        "自动调整比例到" + (graph.view.scale * 100).toString() + "%"
      );
      if (mxUtils.hasScrollbars(graph.container)) {
        graph.container.scrollTop =
          (bounds.y + t.y) * scale -
          Math.max((ch - bounds.height * scale) / 2, 0);
        graph.container.scrollLeft =
          (bounds.x + t.x) * scale -
          Math.max((cw - bounds.width * scale) / 2, 0);
      }
    },
    //原始大小
    scaleRaw() {
      var scale = 1;
      graph.zoomTo(scale);
      this.$message("还原到" + (graph.view.scale * 100).toString() + "%");
    },
    //导入XML
    _import() {
      document.getElementById("importXML").click();
    },

    //导入JSON
    _importJSON() {
      document.getElementById("importJSON").click();
    },

    //导出PDF
    exportPDF() {
      var autoOrigin = true;
      var printconnectionsScale = 1;
      printScale *= 0.75;
      var pf = graph.pageFormat;
      var scale = 1 / graph.pageScale;
      if (autoOrigin) {
        var pageCount = 1;
        if (!isNaN(pageCount)) {
          scale = mxUtils.getScaleForPageCount(pageCount, graph, pf);
          console.log(scale);
        }
      }
      var gb = graph.getGraphBounds();
      var border = 0;
      var x0 = 0;
      var y0 = 0;
      pf = mxRectangle.fromRectangle(pf);
      pf.width = Math.ceil(pf.width * printScale);
      pf.height = Math.ceil(pf.height * printScale);
      scale *= printScale;
      var preview = this.createPrintPreview(
        graph,
        scale,
        pf,
        border,
        x0,
        y0,
        autoOrigin
      );
      preview.open();
      if (print) {
        this.printPreview(preview);
      }
    },
    //生成预览
    createPrintPreview(graph, scale, pf, border, x0, y0, autoOrigin) {
      var preview = new mxPrintPreview(graph, scale, pf, border, x0, y0);
      preview.printBackgroundImage = true;
      preview.autoOrigin = autoOrigin;
      var bg = "#ffffff";
      preview.backgroundColor = bg;
      var writeHead = preview.writeHead;
      preview.writeHead = function (doc) {
        writeHead.apply(this, arguments);
        doc.writeln('<style type="text/css">');
        doc.writeln("@media screen {");
        doc.writeln("  body > div { padding:30px;box-sizing:content-box; }");
        doc.writeln("}");
        doc.writeln("</style>");
      };
      return preview;
    },
    //打印预览
    printPreview(preview) {
      try {
        if (preview.wnd != null) {
          var printFn = function () {
            preview.wnd.focus();
            preview.wnd.print();
            preview.wnd.close();
          };
          window.setTimeout(printFn, 500);
          printFn();
        }
      } catch (e) {}
    },

    //展示JSON
    viewJSON() {
      var enc = new mxCodec(mxUtils.createXmlDocument());
      var node = enc.encode(graph.getModel());
      var file = mxUtils.getXml(node);
      //var jsonObj = JSON.stringify(this.$x2js.xml2js(file));
      //mxUtils.popup(jsonObj, true);
      mxUtils.popup(
        JSON.stringify(this.$x2js.xml2js(file), null, 2).trim(),
        true
      );
    },

    //导出JSON
    exportJSON() {
      var enc = new mxCodec(mxUtils.createXmlDocument());
      var node = enc.encode(graph.getModel());
      var file = mxUtils.getXml(node);
      var jsonObj = JSON.stringify(this.$x2js.xml2js(file));
      //下载文件方法
      var funDownload = function (content, filename) {
        var eleLink = document.createElement("a");
        eleLink.download = filename;
        eleLink.style.display = "none";
        // 字符内容转变成blob地址
        var blob = new Blob([content]);
        eleLink.href = URL.createObjectURL(blob);
        // 触发点击
        document.body.appendChild(eleLink);
        eleLink.click();
        // 然后移除
        document.body.removeChild(eleLink);
      };
      //从浏览器保存文件
      if ("download" in document.createElement("a")) {
        funDownload(jsonObj, "model.json");
      } else {
        this.$message("浏览器不支持导出文件!");
      }
      //mxUtils.popup(jsonObj, true);
    },

    //展示XML
    viewXML() {
      var enc = new mxCodec(mxUtils.createXmlDocument());
      var node = enc.encode(graph.getModel());
      mxUtils.popup(mxUtils.getPrettyXml(node), true);
    },

    //导出XML
    exportXML() {
      var enc = new mxCodec(mxUtils.createXmlDocument());
      var node = enc.encode(graph.getModel());
      var file = mxUtils.getXml(node);
      //同时存入全局变量
      xml = node;
      //下载文件方法
      var funDownload = function (content, filename) {
        var eleLink = document.createElement("a");
        eleLink.download = filename;
        eleLink.style.display = "none";
        // 字符内容转变成blob地址
        var blob = new Blob([content]);
        eleLink.href = URL.createObjectURL(blob);
        // 触发点击
        document.body.appendChild(eleLink);
        eleLink.click();
        // 然后移除
        document.body.removeChild(eleLink);
      };
      //从浏览器保存文件
      if ("download" in document.createElement("a")) {
        funDownload(file, "model.xml");
      } else {
        this.$message("浏览器不支持导出文件!");
      }
    },
    //创建节点类型
    createElement(name) {
      for (var index in elementAttrList) {
        var s = new XMLSerializer();
        if (
          s.serializeToString(elementAttrList[index]).startsWith("<" + name)
        ) {
          return elementAttrList[index];
        }
      }
      var eletype = doc.createElement(name);
      elementAttrList.push(eletype);
      return eletype;
    },

    //处理上传的xml文件
    handle_file(event) {
      var files = event.target.files;
      if (files != null) {
        var file = files[0];
        var reader = new FileReader();
        reader.onload = function (e) {
          var data = e.target.result;
          var model = mxUtils.parseXml(data);
          var dec = new mxCodec(model);
          dec.decode(model.documentElement, graph.getModel());
        };
        reader.readAsText(file);
      }
    },
    //处理上传的json文件
    handle_file2(event) {
      var _this = this;
      var files = event.target.files;
      if (files != null) {
        var file = files[0];
        var reader = new FileReader();
        reader.onload = function (e) {
          var data2 = e.target.result;
          //alert(data2);
          var data = _this.$x2js.js2xml(JSON.parse(data2));
          //alert(data);
          //mxUtils.popup(data, true);
          var model = mxUtils.parseXml(data);
          var dec = new mxCodec(model);
          dec.decode(model.documentElement, graph.getModel());
        };
        reader.readAsText(file);
      }
    },

    //动态新增input
    addAttr() {
      this.form.attrs.push({
        attrName: "",
        attrValue: "",
      });
    },
    //删除最后一个input
    delAttr() {
      this.form.attrs.splice(-1, 1);
    },
    //删除当前input
    delCurAttr(index) {
      this.form.attrs.splice(index, 1);
    },
    //拖拽新增节点类型
    addNewElement(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          var type = this.$options.methods.createElement(this.form.itemName);
          for (var index in this.form.attrs) {
            type.setAttribute(
              this.form.attrs[index].attrName,
              this.form.attrs[index].attrValue + " " + this.form.attrUnit
            );
          }
          this.handleDrop(graph, curFile, curX, curY, type);
          this.dialogFormVisible = false;
          this.form.itemName = "";
          this.form.attrs = [];
        } else {
          return false;
        }
      });
    },
    //关闭弹框
    closeDialog() {
      this.form.itemName = "";
      this.form.attrs = [];
    },
    //保存模型到服务器
    saveModel() {
      if (this.projectId == "") {
        this.$message("未指定项目，请选择项目后再保存！");
        return;
      }
      var enc = new mxCodec(mxUtils.createXmlDocument());
      var node = enc.encode(graph.getModel());
      var file = mxUtils.getXml(node);
      var _this = this;
      this.$axios
        .post("/graph/saveModel", {
          id: _this.projectId,
          file: file,
        })
        .then(function (response) {
          if (response.data.code == 0) {
            _this.$message("保存成功！");
          } else {
            _this.$message("保存模型失败，请重试！");
          }
        })
        .catch(function (error) {
          console.log(error);
          _this.$message("保存模型失败，请重试！");
        });
    },
    //从服务器读取模型
    readModel() {
      var _this = this;
      if (_this.projectId == "") {
        _this.$message("未指定项目，请选择项目后再加载！");
        return;
      }
      this.$axios
        .post("/graph/getModel", {
          id: _this.projectId,
        })
        .then(function (response) {
          if (response.data == "error") {
            _this.$message("获取模型文件错误，请重试！");
          } else {
            var model = mxUtils.parseXml(response.data);
            var dec = new mxCodec(model);
            dec.decode(model.documentElement, graph.getModel());
            _this.$message("模型加载完成！");
          }
        })
        .catch(function (error) {
          console.log(error);
          _this.$message("获取模型文件错误，请重试！");
        });
    },
  },
  mounted() {

    // 连接箭头
    // mxConnectionHandler.prototype.connectImage = new mxImage(
    //   "../../../static/images/connector.gif",
    //   18,
    //   18
    // );
    doc = mxUtils.createXmlDocument();
    var _this = this;

    Bus.$on("curProjectId", (id) => {
      _this.projectId = id;
      console.log(_this.projectId);
    });
    Bus.$on("loadModel", () => {
      getModel(graph);
    });

    tbContainer = document.getElementById("tbContainer");
    container = document.getElementById("container");
    container.style.background = 'url("../../../static/images/bg.svg")';
    headContainer = document.getElementById("headContainer");
    rightContainer = document.getElementById("rightContainer");
    var cellInfo = document.createElement("div");
    cellInfo.id = "cellInfo";
    rightContainer.appendChild(cellInfo);

    model = new mxGraphModel();
    graph = new mxGraph(container, model);
    //设置节点间可以连接
    graph.setConnectable(true);
    //在container中禁用浏览器右键菜单，用于编写自定义菜单
    mxEvent.disableContextMenu(container);
    //允许两个节点间出现双向连接
    graph.setMultigraph(true);
    //从中心/左上缩放
    graph.centerZoom = true;
    //自定义菜单
    graph.popupMenuHandler.factoryMethod = function (menu, cell, evt) {
      return createPopupMenu(graph, menu, cell, evt);
    };
    //按键响应
    const keyHandler = new mxKeyHandler(graph);
    //按delete键删除元素
    keyHandler.bindKey(46, function (evt) {
      if (graph.isEnabled()) {
        graph.removeCells();
      }
    });

    //设置连接线为折线
    graph.connectionHandler.createEdgeState = function (me) {
      var edge = graph.createEdge(
        null,
        null,
        null,
        null,
        null,
        "edgeStyle=orthogonalEdgeStyle"
      );

      return new mxCellState(
        this.graph.view,
        edge,
        this.graph.getCellStyle(edge)
      );
    };
    //设置边缘连接
    graph.getAllConnectionConstraints = function (terminal) {
      // if (terminal != null && this.model.isVertex(terminal.cell)) {
      //   return [
      //     //new mxConnectionConstraint(new mxPoint(0, 0), true),
      //     //new mxConnectionConstraint(new mxPoint(0.5, 0), true),
      //     //new mxConnectionConstraint(new mxPoint(1, 0), true),
      //     new mxConnectionConstraint(new mxPoint(0, 0.5), true),
      //     new mxConnectionConstraint(new mxPoint(1, 0.5), true),
      //     //new mxConnectionConstraint(new mxPoint(0, 1), true),
      //     //new mxConnectionConstraint(new mxPoint(0.5, 1), true),
      //     //new mxConnectionConstraint(new mxPoint(1, 1), true)
      //   ];
      // }
      if (terminal !== null && this.model.isVertex(terminal.cell)) {
        const cell = terminal["cell"];
        const constraints = cell["constraints"];

        if (constraints instanceof Array && constraints.length > 0) {
          return constraints.map((constraint) => {
            return new mxConnectionConstraint(
              new mxPoint(constraint["x"], constraint["y"]),
              constraint["perimeter"]
            );
          });
        }
        // else {
        //   if (terminal.shape.stencil) {
        //     return terminal.shape.stencil.constraints
        //   } else if (terminal.shape.constraints) {
        //     return terminal.shape.constraints
        //   }
        // }
      }
      return null;

      //return null;
    };

    //修正
    mxConstraintHandler.prototype.intersects = function(icon, point, source, existingEdge)
		{
			return (!source || existingEdge) || mxUtils.intersects(icon.bounds, point);
		};
    //设置只有连接点可以连接
    if (graph.connectionHandler.connectImage == null) {
      graph.connectionHandler.isConnectableCell = function (cell) {
        return false;
      };
      mxEdgeHandler.prototype.isConnectableCell = function (cell) {
        return graph.connectionHandler.isConnectableCell(cell);
      };
    }
    //右键可以移动选中节点
    graph.setPanning(true);
    //显示导航线（用于对齐）
    mxGraphHandler.prototype.guidesEnabled = true;
    //允许没有连接元件的线存在
    graph.setAllowDanglingEdges(true);
    //允许左键框选多个节点移动
    new mxRubberband(graph);
    //撤销重做
    undoManager = new mxUndoManager();
    var listener = function (sender, evt) {
      undoManager.undoableEditHappened(evt.getProperty("edit"));
    };
    graph.getModel().addListener(mxEvent.UNDO, listener);
    graph.getView().addListener(mxEvent.UNDO, listener);
    getElements(graph);
    //从浏览器外拖拽至graph内生成节点的监听函数
    mxEvent.addListener(container, "dragover", function (evt) {
      if (graph.isEnabled()) {
        evt.stopPropagation();
        evt.preventDefault();
      }
    });
    mxEvent.addListener(container, "drop", function (evt) {
      if (graph.isEnabled()) {
        evt.stopPropagation();
        evt.preventDefault();

        //取得落点坐标
        var pt = mxUtils.convertPoint(
          graph.container,
          mxEvent.getClientX(evt),
          mxEvent.getClientY(evt)
        );
        var tr = graph.view.translate;
        var scale = graph.view.scale;
        var x = pt.x / scale - tr.x;
        var y = pt.y / scale - tr.y;

        //因为弹框限制，一次只能处理一个元件
        var filesArray = event.dataTransfer.files;
        curFile = filesArray[0];
        curX = x;
        curY = y;
        _this.dialogFormVisible = true;
        document.getElementById("addAttr").click();
      }
    });
    //添加监听函数
    graph
      .getSelectionModel()
      .addListener(mxEvent.CHANGE, function (sender, evt) {
        selectionChanged(graph);
      });

    //创建自定义菜单（删除,全选）
    function createPopupMenu(graph, menu, cell, evt) {
      if (cell != null) {
        menu.addItem("删除", null, function () {
          graph.removeCells();
        });
        // menu.addItem("查看属性", null, function () {
        //   //graph.removeCells();
        //   //var cell2 = graph.getSelectionCell();
        //   //mxUtils.popup(cell2.getId(),true);
        //   var doc = mxUtils.createXmlDocument();
        //   var node = doc.createElement("MyNode");
        //   node.setAttribute("label", "MyLabel");
        //   node.setAttribute("attribute1", "value1");
        //   var cell2 = graph.getSelectionCell();
        //   cell2.setValue(node);
        //   graph.refresh();
        //   mxUtils.popup(cell2.getAttribute("attribute1"),true);
        // });
        menu.addItem("属性", null, function () {
          //graph.removeCells();
          //_this.editFormVisible = true;
          //cell.value.nodeName
          //this.form.itemName
          var cell2 = graph.getSelectionCell();
          if (cell2.value == null){
            _this.editForm.eid = "";
            _this.editForm.name = "";
            _this.editForm.value = "";
            _this.editForm.unit = "";
          }
          else {
            _this.editForm.eid = cell2.value.nodeName;
            _this.editForm.name = cell2.value.attributes[0].nodeName;
            _this.editForm.value = cell2.value.attributes[0].nodeValue;
            _this.editForm.unit = cell2.value.attributes[1].nodeValue;
            //mxUtils.popup(cell2.value.nodeName,true);
            //mxUtils.popup(cell.value.attributes[0].nodeName + ":" + cell.value.attributes[0].nodeValue,true);
          }
          _this.editFormVisible = true;
          //_this.editForm.eid = "123";
          //update();
          // var cell2 = graph.getSelectionCell();
          // var doc = mxUtils.createXmlDocument();
          // var node = doc.createElement(name);
          // node.setAttribute(name, value);
          // node.setAttribute("单位", unit);
          // cell2.setValue(node);
          // graph.refresh();
          //mxUtils.popup(cell2.getAttribute("name"),true);
          //_this.editFormVisible = false;
          //mxUtils.popup(_this.editForm.name,true);
        });
        // menu.addItem("旋转", null, function () {
        //   graph.rotate90();
        // });
      } else {
        menu.addItem("全选", null, function () {
          graph.selectAll();
        });
      }
    }

    //请求所有elements
    function getElements(graph) {
      _this.$axios
        .post("/graph/getElements")
        .then(function (response) {
          if (response.data.code == 0) {
            elementInfo = response.data.elements;
            var basePath = "http://localhost:8443";
            for (var index in elementInfo) {
              curElement = elementInfo[index];
              var image = document.createElement("img");
              image.id = curElement["id"];
              image.src = basePath + curElement["path"];
              image.width = 100;
              image.height = 50;
              var center = document.createElement("center");
              center.appendChild(image);
              tbContainer.appendChild(center);
              var type = createElement(curElement["name"]);
              getAttributes(graph, curElement["id"], type);
              getConnections(graph, curElement, type);
              customFunct(graph, image, type);
            }
          } else {
            _this.$message("获取管道元件信息错误，请重试！");
          }
        })
        .catch(function (error) {
          console.log(error);
          _this.$message("获取管道元件信息错误，请重试！");
        });
    }

    //获取模型文件
    function getModel(graph) {
      // var data = new FormData();
      // data.append();
      _this.$axios
        .post("/graph/getModel", {
          id: _this.projectId,
        })
        .then(function (response) {
          var model = mxUtils.parseXml(response.data);
          var dec = new mxCodec(model);
          dec.decode(model.documentElement, graph.getModel());
          _this.$message("模型加载完成！");
        })
        .catch(function (error) {
          console.log(error);
          _this.$message("获取模型文件错误，请重试！");
        });
    }

    //创建节点类型
    function createElement(name) {
      for (var index in elementAttrList) {
        var s = new XMLSerializer();
        if (
          s.serializeToString(elementAttrList[index]).startsWith("<" + name)
        ) {
          return elementAttrList[index];
        }
      }
      var eletype = doc.createElement(name);
      elementAttrList.push(eletype);
      return eletype;
    }

    //自定义拖拽函数的动作
    function customFunct(graph, image, type) {
      var funct = function (graph, evt, cell, x, y) {
        addCell(graph, image, x, y, type);
      };
      mxUtils.makeDraggable(image, graph, funct, null);
    }

    //toolbar拖拽添加节点
    function addCell(graph, image, x, y, type) {
      var width = image["naturalWidth"] / 2;
      var height = image["naturalHeight"] / 2;
      var style =
        "shape=image;image=" +
        image["src"] +
        ";verticalLabelPosition=bottom;verticalAlign=top";
      var parent = graph.getDefaultParent();
      graph.getModel().beginUpdate();
      try {
        var vertex = graph.insertVertex(
          parent,
          null,
          type,
          x,
          y,
          width,
          height,
          style
        );

        //getConnections(graph, elementID, type);
        var ttt = [];
        //ttt.push({ x: 0, y: 0.5, perimeter: true });
        //ttt.push({ x: 1, y: 0.5, perimeter: true });

        // vertex['constraints'] = [
        //   {x: 0, y: 0.5, perimeter: true},
        //   {x: 1, y: 0.5, perimeter: true}
        // ]
        var len = vertex.value.attributes.length - 5;
        for (var i = 0; i < len / 2; i++) {
          ttt.push({
            x: vertex.getAttribute("连接点横坐标" + i),
            y: vertex.getAttribute("连接点纵坐标" + i),
            perimeter: true,
          });
        }
        vertex["constraints"] = ttt;
        //mxUtils.popup(vertex.value.attributes[0].nodeValue,true);
        //mxUtils.popup(vertex.getAttribute("连接点横坐标0"),true);
      } finally {
        graph.getModel().endUpdate();
      }
    }

    //根据elementID请求attr
    function getAttributes(graph, elementID, type) {
      var data = new FormData();
      data.append("id", elementID);
      _this.$axios
        .post("/graph/getAttributes", {
          eid: elementID,
        })
        .then(function (response) {
          if (response.data.code == 0) {
            var attributes = response.data.attributes;
            for (var index in attributes) {
              var temp = attributes[index];
              type.setAttribute(
                temp["name"],
                temp["value"] + " " + temp["unit"]
              );
              //console.log(temp["unit"]);
            }
          } else {
            _this.$message("获取" + elementID + "号元件属性错误，请重试！");
          }
        })
        .catch(function (error) {
          console.log(error);
          _this.$message("获取" + elementID + "号元件属性错误，请重试！");
        });
    }

    //根据elementID请求connection
    function getConnections(graph, curElement, type) {
      var elementID = curElement["id"];
      var data = new FormData();
      data.append("id", elementID);
      _this.$axios
        .post("/graph/getConnections", {
          eid: elementID,
        })
        .then(function (response) {
          if (response.data.code == 0) {
            var connections = response.data.connections;
            //mxUtils.popup(connections[0]["cx"],true);
            for (var index in connections) {
              var temp = connections[index];
              type.setAttribute("连接点横坐标" + index, temp["cx"]);
              type.setAttribute("连接点纵坐标" + index, temp["cy"]);
              //mxUtils.popup(temp["cy"],true);
              //console.log(temp["unit"]);
            }
          } else {
            _this.$message("获取" + elementID + "号元件连接点错误，请重试！");
          }
        })
        .catch(function (error) {
          console.log(error);
          _this.$message("获取" + elementID + "号元件连接点错误，请重试！");
        });
    }

    //拖拽生成节点，将该图片加入侧边工具栏
    _this.handleDrop = function (graph, file, x, y, type) {
      if (file.type.substring(0, 5) == "image") {
        //生成节点
        var reader = new FileReader();
        reader.onload = function (e) {
          var data = e.target.result;
          var img = new Image();
          img.onload = function () {
            var w = Math.max(1, img.width / 2);
            var h = Math.max(1, img.height / 2);
            var semi = data.indexOf(";");
            if (semi > 0) {
              data =
                data.substring(0, semi) +
                data.substring(data.indexOf(",", semi + 1));
            }
            var parent = graph.getDefaultParent();
            var vertex = graph.insertVertex(
              parent,
              null,
              type,
              x,
              y,
              w,
              h,
              "shape=image;image=" +
                data +
                ";verticalLabelPosition=bottom;verticalAlign=top"
            );
          };
          img.src = data;
          var item = document.createElement("img");
          item.src = img.src;
          item.width = 100;
          item.height = 50;
          var center = document.createElement("center");
          center.appendChild(item);
          tbContainer.appendChild(center);
          addedCustomFunct(graph, item, type);
        };
        reader.readAsDataURL(file);
      }
    };

    //新添加图片（data格式）拖拽函数的动作
    function addedCustomFunct(graph, image, type) {
      var funct = function (graph, evt, cell, x, y) {
        addDataCell(graph, image, x, y, type);
      };
      mxUtils.makeDraggable(image, graph, funct, null);
    }

    //toolbar拖拽添加data格式节点
    function addDataCell(graph, image, x, y, type) {
      var data = image["src"];
      var img = new Image();
      img.onload = function () {
        var w = Math.max(1, img.width / 2);
        var h = Math.max(1, img.height / 2);
        var semi = data.indexOf(";");
        if (semi > 0) {
          data =
            data.substring(0, semi) +
            data.substring(data.indexOf(",", semi + 1));
        }
        var parent = graph.getDefaultParent();
        var vertex = graph.insertVertex(
          parent,
          null,
          type,
          x,
          y,
          w,
          h,
          "shape=image;image=" +
            data +
            ";verticalLabelPosition=bottom;verticalAlign=top"
        );
      };
      img.src = data;
    }

    //动态新增右侧文本框
    function createTextField(graph, form, cell, attribute) {
      var input = form.addText(attribute.nodeName + ":", attribute.nodeValue);
      var applyHandler = function () {
        var newValue = input.value || "";
        var oldValue = cell.getAttribute(attribute.nodeName, "");
        if (newValue != oldValue) {
          graph.getModel().beginUpdate();
          try {
            cell.setAttribute(attribute.nodeName, newValue);
            graph.refresh();
          } finally {
            graph.getModel().endUpdate();
          }
        }
      };
      mxEvent.addListener(input, "keypress", function (evt) {
        if (evt.keyCode == /*enter*/ 13 && !mxEvent.isShiftDown(evt)) {
          input.blur();
        }
      });

      if (mxClient.IS_IE) {
        mxEvent.addListener(input, "focusout", applyHandler);
      } else {
        mxEvent.addListener(input, "blur", applyHandler);
      }
    }

    //更新rightbar内容
    function selectionChanged(graph) {
      var div = document.getElementById("cellInfo");
      graph.container.focus();
      div.innerHTML = "";
      var cell = graph.getSelectionCell();
      if (cell == null || cell.vertex == false) {
        const center = document.createElement("center");
        mxUtils.writeln(center, "未选中元件！");
        div.appendChild(center);
      } else {
        var center = document.createElement("center");
        mxUtils.writeln(center, cell.value.nodeName);
        var form = new mxForm();
        var attrs = cell.value.attributes;
        for (var i = 0; i < attrs.length; i++) {
          createTextField(graph, form, cell, attrs[i]);
          //console.log(attrs[i]);
        }
        //mxUtils.popup(cell.value.attributes[0].nodeName + ":" + cell.value.attributes[0].nodeValue,true);
        var br = document.createElement("br");
        center.appendChild(br);
        center.appendChild(form.getTable());
        div.appendChild(center);
      }
    }
  },
};
</script>

<style scoped>
#graph {
  height: 100%;
  width: 100%;
  margin: 0;
  padding: 0;
}

.main-div {
  height: 100%;
  width: 100%;
}

.head-container {
  overflow: auto;
  position: absolute;
  left: 0;
  top: 0;
  right: 0;
  height: 80px;
  background-color: #f2f6fc;
}

.left-container {
  overflow: auto;
  position: absolute;
  background-color: #f2f6fc;
  left: 0;
  top: 40px;
  bottom: 10px;
  width: 120px;
}

.main-container {
  overflow: auto;
  position: absolute;
  top: 70px;
  left: 120px;
  right: 300px;
  bottom: 10px;
  border: thin solid #2e2d3c;
}

.right-container {
  overflow: auto;
  position: absolute;
  background-color: #f2f6fc;
  width: 300px;
  right: 0;
  bottom: 10px;
  top: 40px;
}
</style>
