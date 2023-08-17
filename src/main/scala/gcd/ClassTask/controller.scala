package ClassTask
import chisel3 . _
import chisel3.util._

class controller extends Module {
val io = IO (new Bundle {
    val op_code = Input(UInt(3.W))
    val zero = Input (Bool())
    val rst=Input (Bool())
    val mem_rd=Output (Bool())
    val load_ir=Output (Bool())
    val halt=Output (Bool())
    val inc_pc=Output (Bool())
    val load_ac=Output (Bool())
    val load_pc=Output (Bool())
    val mem_wr=Output (Bool())
    
    // val result = Output ( UInt (5.W) )
    // val cntin_1= Input(UInt (5.W))
    // val rst= Input(Bool())
    // val load= Input(Bool())
    // val enable=Input(Bool())

})
        io.mem_rd:=0.B
        io.load_ir:=0.B
        io.halt:=0.B
        io.inc_pc:=0.B
        io.load_ac:=0.B
        io.load_pc:=0.B
        io.mem_wr:=0.B

val count = RegInit (0.U(4.W))
when (count===9.U){
    count:= 0.U
}
.otherwise{
    when(count===1.U){
        io.mem_rd:=0.B
        io.load_ir:=0.B
        io.halt:=0.B
        io.inc_pc:=0.B
        io.load_ac:=0.B
        io.load_pc:=0.B
        io.mem_wr:=0.B
    
    }
    .elsewhen(count===2.U){

        io.mem_rd:=1.B
        io.load_ir:=0.B
        io.halt:=0.B
        io.inc_pc:=0.B
        io.load_ac:=0.B
        io.load_pc:=0.B
        io.mem_wr:=0.B
    
    }
    .elsewhen(count===3.U){
        io.mem_rd:=1.B
        io.load_ir:=1.B
        io.halt:=0.B
        io.inc_pc:=0.B
        io.load_ac:=0.B
        io.load_pc:=0.B
        io.mem_wr:=0.B
    
    }
    .elsewhen(count===4.U){
        io.mem_rd:=1.B
        io.load_ir:=1.B
        io.halt:=0.B
        io.inc_pc:=0.B
        io.load_ac:=0.B
        io.load_pc:=0.B
        io.mem_wr:=0.B
    
    }
    .elsewhen(count===5.U){
        io.mem_rd:=0.B
        io.load_ir:=0.B
        when(io.op_code===0.U){
            io.halt:=1.B
        }
        .otherwise{
            io.halt:=0.B
        }
        io.inc_pc:=1.B
        io.load_ac:=0.B
        io.load_pc:=0.B
        io.mem_wr:=0.B
    }
     .elsewhen(count===6.U){
         when(io.op_code===2.U||io.op_code===3.U||io.op_code===4.U||io.op_code===5.U){
            io.mem_rd:=1.B
         }
         .otherwise{
            io.mem_rd:=0.B
         }
        io.load_ir:=0.B
        io.halt:=0.B
        io.inc_pc:=0.B
        io.load_ac:=0.B
        io.load_pc:=0.B
        io.mem_wr:=0.B
    
    }
    .elsewhen(count===7.U){
         when(io.op_code===2.U||io.op_code===3.U||io.op_code===4.U||io.op_code===5.U){
            io.mem_rd:=1.B
         }
         .otherwise{
            io.mem_rd:=0.B
         }
        io.load_ir:=0.B
        io.halt:=0.B
        when(io.op_code===1.U && io.zero===1.U){
            io.inc_pc:=1.B
        }
        .otherwise{
            io.inc_pc:=0.B
        }
        when(io.op_code===2.U||io.op_code===3.U||io.op_code===4.U||io.op_code===5.U){
            io.load_ac:=1.B
         }
         .otherwise{
            io.load_ac:=0.B
         }
        when(io.op_code===7.U){
            io.load_pc:=1.B
        }
        .otherwise{
            io.load_pc:=0.B
        }
        io.mem_wr:=0.B
    
    }
    
    .elsewhen(count===8.U){
         when(io.op_code===2.U||io.op_code===3.U||io.op_code===4.U||io.op_code===5.U){
            io.mem_rd:=1.B
         }
         .otherwise{
            io.mem_rd:=0.B
         }
        io.load_ir:=0.B
        io.halt:=0.B
        when(io.op_code===7.U){
            io.inc_pc:=1.B
        }
        .otherwise{
            io.inc_pc:=0.B
        }
         when(io.op_code===2.U||io.op_code===3.U||io.op_code===4.U||io.op_code===5.U){
            io.load_ac:=1.B
         }
         .otherwise{
            io.load_ac:=0.B
         }
          when(io.op_code===7.U){
            io.load_pc:=1.B
        }
        .otherwise{
            io.load_pc:=0.B
        }
        
        when(io.op_code===6.U){
            io.mem_wr:=1.B
        }
        .otherwise{
            io.mem_wr:=0.B
        }
    
    }
    


    
}

count:=count+1.U

}
