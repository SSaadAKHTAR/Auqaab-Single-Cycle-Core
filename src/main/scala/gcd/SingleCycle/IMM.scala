package SingleCycle
import chisel3._
import chisel3.util . _

class ImmdValGen extends Bundle {
val instr = Input ( UInt (32. W ) )
val immd_se = Output ( UInt (32. W ) )
// val opcode = Input (UInt(7.W))
}
class ImmdValGen1 extends Module {
val io = IO (new ImmdValGen ) 
// Start coding here

 val x=WireInit(io.instr(31,20))
 val imm = Wire(UInt(20.W))

when(x(11)){
    imm :=  Fill(20,"b1".U)
}
.otherwise{
    imm := Fill(20,"b0".U)
}

io.immd_se := Cat(imm , x)





// End your code here
// Well , you can actually write classes too . So , technically you have no
// limit ; )
}


