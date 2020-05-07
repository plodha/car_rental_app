import { Component, OnInit } from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';
import {SelectionModel} from '@angular/cdk/collections';
import { FormControl, FormGroupDirective, FormBuilder, FormGroup, NgForm, Validators } from '@angular/forms';
import {ActivatedRoute, Router ,NavigationEnd} from '@angular/router';
import {ApiService} from '../api.service'
import {Observable} from 'rxjs/Rx';
import { switchMap } from 'rxjs/operators';

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
  displayedColumns: string[] = ['feeCategory', 'cost'];
  dataSource:Invoice[] = [];
  constructor(private router: Router,private actr: ActivatedRoute, private formBuilder: FormBuilder,private api: ApiService) {
    this.actr.data.subscribe((data) => {
       console.log(data);
       var invoice = data.invoice;

       this.dataSource = [
         {feeCategory: 'Estimated Price', cost: invoice.estimatedPrice},
         {feeCategory: 'Late Fee', cost: invoice.lateFee},
         {feeCategory: 'Damage Fee', cost: invoice.damageFee},

       ];
       console.log(invoice)

     });
  }
  ngOnInit(): void {
  }


 /** Gets the total cost of all transactions. */
 getTotalCost() {
   return this.dataSource.map(t => t.cost).reduce((acc, value) => acc + value, 0);
 }

 showDialog()
{
  //alert("Payment charged to your credit card.Thank you for your payment.")
  this.router.navigate(['/reservations/'+localStorage.customerId]);

}
}
