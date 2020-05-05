import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddPricePageComponent } from './add-price-page.component';

describe('AddPricePageComponent', () => {
  let component: AddPricePageComponent;
  let fixture: ComponentFixture<AddPricePageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddPricePageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddPricePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
