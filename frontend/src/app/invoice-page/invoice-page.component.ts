import { Component, OnInit } from '@angular/core';


export interface Invoice {
  feeCategory: string;
  cost: number;
}

@Component({
  selector: 'app-invoice-page',
  templateUrl: './invoice-page.component.html',
  styleUrls: ['./invoice-page.component.scss']
})
export class InvoicePageComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }
  displayedColumns: string[] = ['feeCategory', 'cost'];
 transactions: Invoice[] = [
   {feeCategory: 'Estimated Price', cost: 240.47},
   {feeCategory: 'Late Fee', cost: 500},
   {feeCategory: 'Damage Fee', cost: 200},

 ];

 /** Gets the total cost of all transactions. */
 getTotalCost() {
   return this.transactions.map(t => t.cost).reduce((acc, value) => acc + value, 0);
 }

}
