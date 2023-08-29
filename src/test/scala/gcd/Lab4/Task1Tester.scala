// package Lab4
// import chisel3._
// import chisel3.util
// import org.scalatest._
// import chiseltest._
// import chiseltest.experimental.TestOptionBuilder . _
// import scala.util.Random

// import fn3._


// class Task14Tester extends FreeSpec with ChiselScalatestTester {
//     "branch control tester" in {
//         test(new Branch){ a=>
// val array_fn3 = Array(fn_beq , fn_bne, fn_bge , fn_bgeu, fn_bltu , fn_blt)
// for ( i <- 0 until 100){
//     val fn = Random.nextInt(5)
//     val function3 = array_fn3(fn)
//     val src_a =Random.nextInt(99999) & 0xFFFFFFFFL
//     val src_b = Random.nextInt(99999) & 0xFFFFFFFFL

//     //val res = Reg(Bool())
    
//     val result = function3 match {
// case fn_beq => (src_a.toInt === src_b.toInt)
// case fn_bge => src_a.asSInt() >= src_b.asSInt()
// case fn_bgeu => src_a.asUInt >=  src_b.asUInt()
// case fn_blt =>  if ( src_a.toInt < src_b.toInt ) 1.B else 0.B
// case fn_bltu => src_a.asUInt < src_b.asUInt()
// case fn_bne => src_a.asUInt != src_b.asUInt
// case _ => 0.B
// }
//     val resul = result
   
//     a.io.fnct3.poke(fn . asUInt)
//     a.io.arg_x.poke(src_a . asUInt)
//     a.io.arg_y.poke(src_b . asUInt)
//     a.io.branch.poke(1.B)
//     a.io.br_taken.expect() 
    
// }

// }   
// }

// }