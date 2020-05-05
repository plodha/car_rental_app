import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditPricePageComponent } from './edit-price-page.component';

describe('EditPricePageComponent', () => {
  let component: EditPricePageComponent;
  let fixture: ComponentFixture<EditPricePageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditPricePageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditPricePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
