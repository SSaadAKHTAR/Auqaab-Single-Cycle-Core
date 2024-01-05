package SingleCycle
import chisel3._
import chisel3.util._
import chisel3.util.experimental.loadMemoryFromFile
import scala.io.Source

class SyncMem ( initFile : String ) extends Module{
    val io = IO(new Bundle{
        val add = Input(UInt(32.W))
        // val datain = Input(UInt(32.W))
        // val Wen = Input(Bool())
        val instOut = Output(UInt(32.W))
    })
    // io.datain:=0.U
    // io.Wen:=0.B
    val SyncMem = SyncReadMem (1024,UInt(32.W ))
    loadMemoryFromFile ( SyncMem , initFile ) 
    io.instOut := SyncMem.read (io.add / 4.U ) 


}