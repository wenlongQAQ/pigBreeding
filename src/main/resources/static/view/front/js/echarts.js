// $(function () {
//     ceshis();
// 	ceshis1();
//     function ceshis() {
//         var myChart = echarts.init(document.getElementById('chart1'));
//
//         option = {
//             tooltip: {
//                 trigger: 'axis'
//             },
//             toolbox: {
//                 show: true,
//                 feature: {
//                     mark: {
//                         show: true
//                     },
//                     dataView: {
//                         show: true,
//                         readOnly: false
//                     },
//                     magicType: {
//                         show: true,
//                         type: ['line', 'bar']
//                     },
//                     restore: {
//                         show: true
//                     },
//                     saveAsImage: {
//                         show: true
//                     }
//                 }
//             },
//             grid: {
//                 top: 'middle',
//                 left: '3%',
//                 right: '4%',
//                 bottom: '3%',
//                 top: '10%',
//                 containLabel: true
//             },
//             legend: {
//                 data: ['湿度', '温度', '甲烷','氨气','硫化氢','二氧化碳'],
//                 textStyle: {
//                     color: "#fff"
//                 }
//
//             },
//             xAxis: [{
//                 type: 'category',
// 			     // ToDo
//                 data: ['1:00', '2:00', '3:00', '4:00', '5:00', '6:00', '7:00', '8:00', '9:00', '10:00', '11:00', '12:00',
//                     '13:00', '14:00', '15:00', '16:00', '17:00', '18:00', '19:00', '20:00', '21:00', '22:00','23:00','00:00'
//                 ],
//                 axisLabel: {
//                     show: true,
//                     textStyle: {
//                         color: "#ebf8ac" //X轴文字颜色
//                     }
//                 },
//                 axisLine: {
//                     lineStyle: {
//                         color: '#01FCE3'
//                     }
//                 },
//             }],
//             yAxis: [
//                 {
//                    type: 'value',
//                    name: '温度',
//                    min:12,  //取0为最小刻度
//                    max: 100, //取100为最大刻度
//                    min:'dataMin', //取最小值为最小刻度
//                    max: 'dataMax', //取最大值为最大刻度
// 				   boundaryGap: ['5%', '5%'],//留白大小，坐标轴两边留
//                    min: function(value) {//取最小值向下取整为最小刻度
//                                return Math.floor(value.min)
//                              },
//                    max: function(value) {//取最大值向上取整为最大刻度
//                                return  Math.ceil(value.max)
//                              },
//                    scale: true, //自适应
//                    minInterval: 0.1, //分割刻度
//                    axisLabel: {
//                        formatter: '{value}°C/%',
//
//                        textStyle: {
//                            color: "#2EC7C9" //X轴文字颜色
//                        }
//                    },
//                    axisLine: {
//                        lineStyle: {
//                            color: '#01FCE3'
//                        },
//                    },
//                 },
// 				{
// 				    type: 'value',
// 				    name: '含量',
// 					min:12,  //取0为最小刻度
// 					max: 100, //取100为最大刻度
// 					min:'dataMin', //取最小值为最小刻度
// 					max: 'dataMax', //取最大值为最大刻度
// 					min: function(value) {//取最小值向下取整为最小刻度
// 					            return Math.floor(value.min)
// 					          },
// 					max: function(value) {//取最大值向上取整为最大刻度
// 					            return  Math.ceil(value.max)
// 					          },
// 					scale: true, //自适应
// 					minInterval: 0.1, //分割刻度
// 					 boundaryGap: ['5%', '5%'],//留白大小，坐标轴两边留
// 				    axisLabel: {
// 				        formatter: '{value} ppm',
//
// 				        textStyle: {
// 				            color: "#2EC7C9" //X轴文字颜色
// 				        }
// 				    },
// 				    axisLine: {
// 				        lineStyle: {
// 				            color: '#01FCE3'
// 				        },
// 				    },
// 				},
//             ],
//             series: [
//
//                 {
//                     name: '湿度',
//                     type: 'bar',
//                     itemStyle: {
//                         normal: {
//                             barBorderRadius: 5,
//                             color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
//                                 offset: 0,
//                                 color: "#00FFE3"
//                             },
//                                 {
//                                     offset: 1,
//                                     color: "#4693EC"
//                                 }
//                             ])
//                         }
//                     },
// 					//TODO
//                     data: [26.4, 25.3,28.7, 70.7, 75.6, 82.2, 48.7, 58.8, 16.0, 32.3, 30.3, 21.3, 66.3, 41.3, 15.3, 30.3, 22.3, 16.0, 82.3, 72.3, 62.3, 52.3, 42.3, 36.6],
//                 },
//                 {
//                     name: '温度',
//                     type: 'bar',
//                     itemStyle: {
//                         normal: {
//                             barBorderRadius: 5,
//                             color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
//                                 offset: 0,
//                                 color: "#C1B2EA"
//                             },
//                                 {
//                                     offset: 1,
//                                     color: "#8362C6"
//                                 }
//                             ])
//                         }
//                     },
// 					//TODO
//                     data: [26.4, 13.4,28.7, 70.7, 75.6, 82.2, 48.7, 58.8, 16.0, 32.3, 30.3, 21.3, 66.3, 41.3, 15.3, 30.3, 22.3, 18.3, 82.3, 72.3, 62.3, 52.3, 42.3, 36.6],
//                 },
//                 {
//                     name: '甲烷',
//                     type: 'line',
//                     yAxisIndex: 1,
// 					//TODO
//                     data: [26.4, 25.3,28.7, 70.7, 75.6, 82.2, 48.7, 58.8, 16.0, 32.3, 30.3, 21.3, 66.3, 41.3, 15.3, 30.3, 22.3, 12.3, 82.3, 72.3, 62.3, 52.3, 42.3, 36.6],
//                     lineStyle: {
//                         normal: {
//                             width: 5,
//                             color: {
//                                 type: 'linear',
//
//                                 colorStops: [{
//                                     offset: 0,
//                                     color: '#AAF487' // 0% 处的颜色
//                                 },
//                                     {
//                                         offset: 0.4,
//                                         color: '#47D8BE' // 100% 处的颜色
//                                     }, {
//                                         offset: 1,
//                                         color: '#47D8BE' // 100% 处的颜色
//                                     }
//                                 ],
//                                 globalCoord: false // 缺省为 false
//                             },
//                             shadowColor: 'rgba(71,216,190, 0.5)',
//                             shadowBlur: 10,
//                             shadowOffsetY: 7
//                         }
//                     },
//                     itemStyle: {
//                         normal: {
//                             color: '#AAF487',
//                             borderWidth: 10,
//                             borderColor: "#AAF487"
//                         }
//                     },
//                     smooth: true,
//                 },
//                 {
//                     name: '氨气',
//                     type: 'line',
//                     yAxisIndex: 1,
// 					//TODO
//                     data: [26.4, 22.8,28.7, 70.7, 75.6, 82.2, 48.7, 58.8, 16.0, 32.3, 30.3, 21.3, 66.3, 41.3, 15.3, 30.3, 22.3, 12.3, 82.3, 72.3, 62.3, 52.3, 42.3, 36.6],
//                     lineStyle: {
//                         normal: {
//                             width: 5,
//                             color: {
//                                 type: 'linear',
//
//                                 colorStops: [{
//                                     offset: 0,
//                                     color: '#aaaaff' // 0% 处的颜色
//                                 },
//                                     {
//                                         offset: 0.4,
//                                         color: '#aa55ff' // 100% 处的颜色
//                                     }, {
//                                         offset: 1,
//                                         color: '#aa00ff' // 100% 处的颜色
//                                     }
//                                 ],
//                                 globalCoord: false // 缺省为 false
//                             },
//                             shadowColor: 'rgba(71,216,190, 0.5)',
//                             shadowBlur: 10,
//                             shadowOffsetY: 7
//                         }
//                     },
//                     itemStyle: {
//                         normal: {
//                             color: '#aa55ff',
//                             borderWidth: 10,
//                             borderColor: "#aa55ff"
//                         }
//                     },
//                     smooth: true,
//                 },
// 				{
// 				    name: '硫化氢',
// 				    type: 'line',
// 				    yAxisIndex: 1,
// 					//TODO
// 				    data: [26.4,29.4, 28.7, 70.7, 75.6, 82.2, 48.7, 58.8, 16.0, 32.3, 30.3, 21.3, 66.3, 41.3, 15.3, 30.3, 22.3, 12.3, 82.3, 72.3, 62.3, 52.3, 42.3, 36.6],
// 				    lineStyle: {
// 				        normal: {
// 				            width: 5,
// 				            color: {
// 				                type: 'linear',
// 				                colorStops: [{
// 				                    offset: 0,
// 				                    color: '#aaff7f' // 0% 处的颜色
// 				                },
// 				                    {
// 				                        offset: 0.4,
// 				                        color: '#aaff00' // 100% 处的颜色
// 				                    }, {
// 				                        offset: 1,
// 				                        color: '#00ff00' // 100% 处的颜色
// 				                    }
// 				                ],
// 				                globalCoord: false // 缺省为 false
// 				            },
// 				            shadowColor: 'rgba(71,216,190, 0.5)',
// 				            shadowBlur: 10,
// 				            shadowOffsetY: 7
// 				        }
// 				    },
// 				    itemStyle: {
// 				        normal: {
// 				            color: '#F7AD3E',
// 				            borderWidth: 10,
// 				            borderColor: "#F7AD3E"
// 				        }
// 				    },
// 				    smooth: true,
// 				},
// 				{
// 				    name: '二氧化碳',
// 				    type: 'line',
// 				    yAxisIndex: 1,
// 					//TODO
// 				    data: [26.4,28.4, 28.7, 70.7, 75.6, 82.2, 48.7, 58.8, 16.0, 32.3, 30.3, 21.3, 66.3, 41.3, 15.3, 30.3, 22.3, 12.3, 82.3, 72.3, 62.3, 52.3, 42.3, 36.6],
// 				    lineStyle: {
// 				        normal: {
// 				            width: 5,
// 				            color: {
// 				                type: 'linear',
//
// 				                colorStops: [{
// 				                    offset: 0,
// 				                    color: '#ffaaff' // 0% 处的颜色
// 				                },
// 				                    {
// 				                        offset: 0.4,
// 				                        color: '#ff55ff' // 100% 处的颜色
// 				                    }, {
// 				                        offset: 1,
// 				                        color: '#ff00ff' // 100% 处的颜色
// 				                    }
// 				                ],
// 				                globalCoord: false // 缺省为 false
// 				            },
// 				            shadowColor: 'rgba(71,216,190, 0.5)',
// 				            shadowBlur: 10,
// 				            shadowOffsetY: 7
// 				        }
// 				    },
// 				    itemStyle: {
// 				        normal: {
// 				            color: '#F7AD3E',
// 				            borderWidth: 10,
// 				            borderColor: "#F7AD3E"
// 				        }
// 				    },
// 				    smooth: true,
// 				}
//             ]
//         };
//
//         // 使用刚指定的配置项和数据显示图表。
//         myChart.setOption(option);
//         window.addEventListener("resize",function(){
//             myChart.resize();
//         });
//     }
// function ceshis1() {
//         var myChart = echarts.init(document.getElementById('chart2'));
// 		/**
// 		 * TODO
// 		 */
//         var ydata = [
//             {
//                 name: '=>1个月',
//                 value: 15
//             },
//             {
//                 name: '1~3个月',
//                 value: 14
//             },
//             {
//                 name: '3~6个月',
//                 value: 10
//             },
//             {
//                 name: '6~12个月',
//                 value: 7.9
//             },
//             {
//                 name: '<=12个月',
//                 value: 6.7
//             },
//         ];
//         var color = ["#8d7fec", "#5085f2", "#e75fc3", "#f87be2", "#f2719a"]
//         var xdata = [ '=>1个月', '1~3个月', '3~6个月','6~12个月','<=12个月' ];
//         option = {
//             color: color,
//             legend: {
//                 orient: "vartical",
//                 x: "left",
//                 top: "center",
//                 left: "53%",
//                 bottom: "0%",
//                 data: xdata,
//                 itemWidth: 5,
//                 itemHeight: 5,
//                 textStyle: {
//                     color: '#fff'
//                 },
//                 formatter: function(name) {
//                     return '' + name
//                 }
//             },
//             series: [{
//                 type: 'pie',
//                 clockwise: false, //饼图的扇区是否是顺时针排布
//                 minAngle: 2, //最小的扇区角度（0 ~ 360）
//                 radius: ["20%", "60%"],
//                 center: ["30%", "45%"],
//                 avoidLabelOverlap: false,
//                 itemStyle: { //图形样式
//                     normal: {
//                         borderColor: '#ffffff',
//                         borderWidth: 1,
//                     },
//                 },
//                 label: {
//                     normal: {
//                         show: false,
//                         position: 'center',
//                         formatter: '{text|{b}}\n{c} ({d}%)',
//                         rich: {
//                             text: {
//                                 color: "#fff",
//                                 fontSize: 14,
//                                 align: 'center',
//                                 verticalAlign: 'middle',
//                                 padding: 8
//                             },
//                             value: {
//                                 color: "#8693F3",
//                                 fontSize: 24,
//                                 align: 'center',
//                                 verticalAlign: 'middle',
//                             },
//                         }
//                     },
//                     emphasis: {
//                         show: true,
//                         textStyle: {
//                             fontSize: 24,
//                         }
//                     }
//                 },
//                 data: ydata
//             }]
//         };
//         myChart.setOption(option);
//
//         setTimeout(function() {
//             myChart.on('mouseover', function(params) {
//                 if (params.name == ydata[0].name) {
//                     myChart.dispatchAction({
//                         type: 'highlight',
//                         seriesIndex: 0,
//                         dataIndex: 0
//                     });
//                 } else {
//                     myChart.dispatchAction({
//                         type: 'downplay',
//                         seriesIndex: 0,
//                         dataIndex: 0
//                     });
//                 }
//             });
//
//             myChart.on('mouseout', function(params) {
//                 myChart.dispatchAction({
//                     type: 'highlight',
//                     seriesIndex: 0,
//                     dataIndex: 0
//                 });
//             });
//             myChart.dispatchAction({
//                 type: 'highlight',
//                 seriesIndex: 0,
//                 dataIndex: 0
//             });
//         }, 1000);
//
//         // 使用刚指定的配置项和数据显示图表。
//         /*myChart.setOption(option);*/
//         window.addEventListener("resize",function(){
//             myChart.resize();
//         });
//     }
// });