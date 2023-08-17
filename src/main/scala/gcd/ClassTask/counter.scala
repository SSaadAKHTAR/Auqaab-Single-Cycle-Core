package ClassTask
import chisel3 . _
import chisel3.util._

class counter extends Module {
val io = IO (new Bundle {
    val result = Output ( UInt (5.W) )
    val cntin_1= Input(UInt (5.W))
    val rst= Input(Bool())
    val load= Input(Bool())
    val enable=Input(Bool())

})
val regResult = withReset(reset.asBool()){RegInit(0.U(5.W))}
    regResult:= Mux(io.enable,Mux(io.load,io.cntin_1,Mux(io.rst,0.U,regResult+1.U)),regResult)

    io.result:=regResult
}