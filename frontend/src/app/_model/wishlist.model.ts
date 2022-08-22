import { Product } from "./product.model";

export class Wishlist {
    userName: string;
    productId: number;
    productName: string;
    productDiscountedPrice: number;
    productImageLink: string;

    constructor(product: Product) {
        this.userName = localStorage.getItem("userName")!;
        this.productId = product.productId;
        this.productName = product.productName
        this.productDiscountedPrice = product.productDiscountedPrice;
        this.productImageLink = product.productImageLink
    }
}