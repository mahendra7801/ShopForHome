import { Component, OnInit } from '@angular/core';
import { DiscountService } from '../_services/discount.service';
import { SalesReportService } from '../_services/sales-report.service';

@Component({
  selector: 'app-order-page',
  templateUrl: './order-page.component.html',
  styleUrls: ['./order-page.component.css']
})
export class OrderPageComponent implements OnInit {

  product: any = [];
  userName: string = localStorage.getItem("userName")!;
  constructor(private salesService: SalesReportService,
    private discountService: DiscountService) { }

  grandTotal: number = 0;
  discount: number = 0;
  ngOnInit(): void {
    this.getMyOrders();
  }

  getMyOrders() {
    let sample: any = [];

    let userName = localStorage.getItem("userName")!;
    this.salesService.getSalesReport().forEach(
      (res: any) => {
        this.product = res;
        for (let i = 0; i < this.product.length; i++) {
          console.log(this.product);
          if (userName === this.product[i].user.userName) {
            this.grandTotal = this.grandTotal + this.product[i].total;
          }
        }

        this.discountService.getUserCoupons().forEach(
          (a: any) => {
            sample = a;
            for (let i = 0; i < sample.length; i++) {
              if (userName === sample[i].user.userName) {
                console.log(sample[i]);
                this.discount = sample[i].discountAmount;
                console.log(this.discount);
              }
            }
            console.log(this.discount);
            this.grandTotal = this.grandTotal - this.discount;
          }
        )
      }
    )
  }
}
