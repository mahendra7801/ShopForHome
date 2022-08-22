import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
//import { Product } from '../_model/product.model';
import { Product } from '../_model/product.model';
import { ProductServiceService } from '../_services/product-service.service';

@Component({
  selector: 'app-update-product',
  templateUrl: './update-product.component.html',
  styleUrls: ['./update-product.component.css']
})
export class UpdateProductComponent implements OnInit {
  productId!: number;
  product: Product = {
    productId: 0,
    productName: '',
    productDescription: '',
    productDiscountedPrice: 0,
    productActualPrice: 0,
    productCategory: '',
    productStock: 0,
    productImageLink: ''
  };
  constructor(
    private productService: ProductServiceService,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.productId = this.route.snapshot.params['productId'];
    console.log(this.productId);
    this.productService.getProductById(this.productId).subscribe(data => {
      this.product = data;
    },
      (error) => console.log(error));
  }

  onSubmit() {
    this.productService.updateProduct(this.productId, this.product).subscribe(data => {
      this.goToProductList();
    },
      (error) => console.log(error))
  }

  goToProductList() {
    this.router.navigate(['/addNewProduct']);
  }
}
