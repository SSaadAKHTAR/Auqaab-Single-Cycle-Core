package ClassTask
import chisel3._
import chisel3.util._

class control extends Module {
val io = IO (new Bundle {
val phase = Input(UInt(3.W))
val zero = Input(Bool())
val op_code = Input(UInt(3.W))

val sel = Output(Bool())
val rd = Output(Bool())
val id_ir = Output(Bool())
val inc_pc = Output(Bool())
val halt = Output(Bool())
val id_pc = Output(Bool())
val data_e = Output(Bool())
val id_ac = Output(Bool())
val wr = Output(Bool())

})
when(io.phase===0.U || io.phase===1.U || io.phase===2.U || io.phase===3.U){
    io.sel:= 1.B
}
.otherwise{
    io.sel:= 0.B
}
io.rd := ((io.phase === 1.U || io.phase === 2.U || io.phase === 3.U) || (io.phase === 5.U || io.phase === 6.U || io.phase === 7.U) && (io.op_code === 2.U || io.op_code === 3.U || io.op_code === 4.U || io.op_code === 5.U))


when(io.phase===2.U || io.phase===3.U){
    io.id_ir:=1.B
}
.otherwise{
    io.id_ir:=0.B
}
io.inc_pc := (io.phase === 4.U || (io.phase === 6.U && io.zero === 0.U && io.op_code === 1.U))


when(io.phase===4.U && io.op_code===0.U){
    io.halt:= 1.B
}
.otherwise{
    io.halt:= 0.B
}
io.id_pc := ((io.phase === 6.U || io.phase === 7.U) && (io.op_code === 7.U))


 io.data_e := ((io.phase === 6.U || io.phase === 7.U) && (io.op_code === 6.U))


io.id_ac := (io.phase === 7.U && (io.op_code === 2.U || io.op_code === 3.U || io.op_code === 4.U || io.op_code === 5.U))


when(io.phase===7.U && io.op_code===6.U){
    io.wr:= 1.U
}
.otherwise{
    io.wr:=0.U
}



}