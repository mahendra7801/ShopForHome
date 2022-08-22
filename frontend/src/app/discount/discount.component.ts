import { NgForOf } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { DiscountService } from '../_services/discount.service';
import { UserService } from '../_services/user.service';

@Component({
  selector: 'app-discount',
  templateUrl: './discount.component.html',
  styleUrls: ['./discount.component.css']
})
export class DiscountComponent implements OnInit {

  discounts: any = [];
  userDetails: any = [];
  userCoupons: any = [];
  sample: any;
  constructor(
    private discountService: DiscountService,
    private userService: UserService
  ) { }

  ngOnInit(): void {
    this.getCoupons();
    this.getUserDetails();
    this.getUserCoupons();
  }

  getUserDetails() {
    this.userService.getUsers().subscribe(
      (resp) => {
        console.log(resp);
        this.sample = resp;
        var count = Object.keys(resp).length;
        var j = 0;
        for (let i = 0; i < count; i++) {
          if (this.sample[i].role[0].roleName === "User") {
            this.userDetails[j] = this.sample[i];
            j = j + 1;
            console.log(this.userDetails);
          }
          else {
            continue;
          }
        }
      },
      (err) => {
        console.log(err);
      }
    );
  }

  addCoupon(discountform: NgForm) {
    console.log(discountform);
    return this.discountService.addCoupon(discountform.value).subscribe(
      (resp) => {
        console.log(resp);
        alert("coupon added successfully!");
        this.getCoupons();
      }, (error) => console.log(error)
    );
  }

  getCoupons() {

    return this.discountService.getCoupons().subscribe(
      (resp) => {
        this.discounts = resp;
        console.log(this.discounts);
      }, (error) => console.log(error)
    );
  }

  deleteCoupon(id: number) {
    return this.discountService.deleteCoupon(id).subscribe(
      (res) => {
        console.log(res);
        this.getCoupons();
      }, (error) => console.log(error)
    );
  }

  giveDiscount(discountselect: NgForm, user: any) {
    var amnt: number = +discountselect.value.discountCoupon
    console.log(amnt);
    console.log(user);
    return this.discountService.addUserCoupon(user, amnt).subscribe(
      (res) => {
        console.log(res);
        this.getUserCoupons();
      },
      (error) => console.log(error)
    );
  }

  getUserCoupons() {
    this.discountService.getUserCoupons().subscribe(
      (res) => {
        this.userCoupons = res;
        console.log(this.userCoupons);
      },
      (error) => console.log(error)
    );
  }

  removeDiscount(id: number) {

    this.discountService.removeDiscount(id).subscribe(
      (res) => {
        console.log(res);
        this.getUserCoupons();
      },
      (error) => console.log(error)
    );
  }
}