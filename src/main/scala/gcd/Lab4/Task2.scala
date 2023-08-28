package Lab4

import chisel3._
import chisel3.util . _
class LM_IO_Interface_ImmdValGen extends Bundle {
val instr = Input ( SInt (32. W ) )
val immd_se = Output ( SInt (32. W ) )
// val opcode = Input (UInt(7.W))
}
class ImmdValGen4 extends Module {
val io = IO (new LM_IO_Interface_ImmdValGen ) 
// Start coding here

 val x=WireInit(io.instr(31,20).asSInt())

io.immd_se:= x




// End your code here
// Well , you can actually write classes too . So , technically you have no
// limit ; )
}


