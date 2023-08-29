package Lab4


import chisel3 . _
import chisel3 . util . _

object fn3 {
    val fn_beq = 0.U(3.W)
    val fn_bne = 1.U(3.W)
    val fn_blt = 2.U(3.W)
    val fn_bge = 3.U(3.W)
    val fn_bltu = 4.U(3.W)
    val fn_bgeu = 5.U(3.W)
}


import fn3._
class LM_IO_Interface_BranchControl extends Bundle {
val fnct3 = Input ( UInt (3. W ) )
val branch = Input ( Bool () )
val arg_x = Input ( UInt (32. W ) )
val arg_y = Input ( UInt (32. W ) )
val br_taken = Output (UInt(1.W) )
}
class Branch extends Module { 
val io = IO (new LM_IO_Interface_BranchControl )
// Start Coding here
when(io.branch===1.B){
when(io.fnct3===fn_beq){
    when(io.arg_x===io.arg_y){
        io.br_taken:=1.U 
    }
    .otherwise{
        io.br_taken:=0.U
    }
}
.elsewhen(io.fnct3===fn_bne){
    when(io.arg_x=/=io.arg_y){
        io.br_taken:=1.U
    }
    .otherwise{
        io.br_taken:=0.U
    }
}
.elsewhen(io.fnct3===fn_blt){
    when(io.arg_x<io.arg_y){
        io.br_taken:=1.U
    } 
    .otherwise{
        io.br_taken:=0.U
    }
}
.elsewhen(io.fnct3===fn_bge){
    when(io.arg_x >= io.arg_y){
        io.br_taken:=1.U
    }
     .otherwise{
        io.br_taken:=0.U
    }
}
.elsewhen(io.fnct3===fn_bltu){
    when(io.arg_x<io.arg_y){
        io.br_taken:=1.U
    }
    .otherwise{
        io.br_taken:=0.U
    }
    
}
.elsewhen(io.fnct3===fn_bgeu){
        when(io.arg_x >= io.arg_y){
        io.br_taken:=1.U
    }
     .otherwise{
        io.br_taken:=0.U
    }
    }
    .otherwise{
        io.br_taken:=0.U
    }}
    .otherwise{
         io.br_taken:=0.U
    }
// End your code here
// Well , you can actually write classes too . So , technically you have no
// limit ; )
}

