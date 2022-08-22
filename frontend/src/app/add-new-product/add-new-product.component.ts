import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { Product } from '../_model/product.model';
import { ProductServiceService } from '../_services/product-service.service';

@Component({
  selector: 'app-add-new-product',
  templateUrl: './add-new-product.component.html',
  styleUrls: ['./add-new-product.component.css']
})
export class AddNewProductComponent implements OnInit {
  productId!: number;
  products: any;
  product: Product = {
    productId: 0,
    productName: "",
    productDescription: "",
    productActualPrice: 0.0,
    productDiscountedPrice: 0.0,
    productCategory: "",
    productStock: 0,
    productImageLink: ""
  }
  constructor(private productService: ProductServiceService,
    private router: Router) { }

  ngOnInit(): void {
  }

  addProduct(productForm: NgForm) {
    this.productService.addProduct(this.product).subscribe(
      (response: Product) => {
        productForm.reset();
      },
      (error: HttpErrorResponse) => {
        console.log(error);
      }
    );
  }

  getProducts() {
    this.productService.getProducts().subscribe(
      (resp) => {
        console.log(resp);
        this.products = resp;
      }, (error) => console.log(error)
    );
  }

  updateProduct(product: Product) {
    this.productId = product.productId;
    console.log(this.productId);

    this.router.navigate(['updateProduct', this.productId]);
  }

  deleteProduct(product: Product) {
    this.productId = product.productId;
    this.productService.deleteProduct(this.productId).subscribe(data => {
      console.log(data);
      this.getProducts();
    })
  }
}
