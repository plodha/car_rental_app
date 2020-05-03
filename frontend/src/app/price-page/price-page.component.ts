import { Component, OnInit, ViewChild } from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';
import {SelectionModel} from '@angular/cdk/collections';


export interface Price {
  id:number;
  vehicleClass: string;
  vehicleSize: string;
  lateFee: string;
  hourlyFee: string;


}
const ELEMENT_DATA: Price[] = [
  {id: 1, vehicleClass:'Compact',vehicleSize: '4',  lateFee:'$ 123.68', hourlyFee:'$ 70.00'},
  {id: 2, vehicleClass:'Luxury',vehicleSize: '4',  lateFee:'$ 83.68', hourlyFee:'$ 90.00'},
  {id: 3,  vehicleClass:'Van',vehicleSize: '7', lateFee:'$ 123.68', hourlyFee:'$ 110.00'},
  {id: 4, vehicleClass:'Compact',vehicleSize: '4',  lateFee:'$ 123.68', hourlyFee:'$ 70.00'},
  {id: 5, vehicleClass:'Luxury',vehicleSize: '4',  lateFee:'$ 83.68', hourlyFee:'$ 90.00'},
  {id: 6,  vehicleClass:'Van',vehicleSize: '7', lateFee:'$ 123.68', hourlyFee:'$ 110.00'},
  {id: 7, vehicleClass:'Compact',vehicleSize: '4',  lateFee:'$ 123.68', hourlyFee:'$ 70.00'},
  {id: 8, vehicleClass:'Luxury',vehicleSize: '4',  lateFee:'$ 83.68', hourlyFee:'$ 90.00'},
  {id: 9,  vehicleClass:'Van',vehicleSize: '7', lateFee:'$ 123.68', hourlyFee:'$ 110.00'},
  {id: 10, vehicleClass:'Compact',vehicleSize: '4',  lateFee:'$ 123.68', hourlyFee:'$ 70.00'},
  {id: 11, vehicleClass:'Luxury',vehicleSize: '4',  lateFee:'$ 83.68', hourlyFee:'$ 90.00'},
  {id: 12,  vehicleClass:'Van',vehicleSize: '7', lateFee:'$ 123.68', hourlyFee:'$ 110.00'},
];
@Component({
  selector: 'app-price-page',
  templateUrl: './price-page.component.html',
  styleUrls: ['./price-page.component.scss']
})
export class PricePageComponent implements OnInit {

  displayedColumns: string[] = ['id', 'vehicleClass', 'vehicleSize','lateFee','hourlyFee','star'];
  dataSource: MatTableDataSource<Price>;


  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
  @ViewChild(MatSort, {static: true}) sort: MatSort;
  constructor() {
    this.dataSource = new MatTableDataSource(ELEMENT_DATA);

  }

  ngOnInit(): void {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

}
