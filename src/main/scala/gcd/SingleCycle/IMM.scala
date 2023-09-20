package SingleCycle
import chisel3._
import chisel3.util . _

class ImmdValGen extends Bundle {
val imm = Input ( UInt (12. W ) )
val immd_se = Output ( UInt (32. W ) )
// val opcode = Input (UInt(7.W))
}
class ImmdValGen1 extends Module {
val io = IO (new ImmdValGen ) 
// Start coding here



io.immd_se := Cat(Fill(19,io.imm(11)) , io.imm)





// End your code here
// Well , you can actually write classes too . So , technically you have no
// limit ; )
}


