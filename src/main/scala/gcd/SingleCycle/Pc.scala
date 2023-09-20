package SingleCycle
import chisel3._
import chisel3.util._
class ProgramCounter extends Module{
    val io=IO(new Bundle{   
        val new_addr = Output (UInt(32.W))
        val input = Input(UInt(32.W))
    })
    val calNewAddr = RegInit(0.U(32.W))
    io.new_addr :=  io.input
    
    
    
}